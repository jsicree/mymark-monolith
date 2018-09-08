package com.mymark.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mymark.app.data.domain.CartLineItem;
import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.domain.InventoryItem;
import com.mymark.app.data.domain.Order;
import com.mymark.app.data.domain.OrderLineItem;
import com.mymark.app.data.domain.Product;
import com.mymark.app.data.enums.InventoryStatus;
import com.mymark.app.data.enums.OrderStatus;
import com.mymark.app.jpa.repository.CartLineItemRepository;
import com.mymark.app.jpa.repository.CustomerRepository;
import com.mymark.app.jpa.repository.InventoryItemRepository;
import com.mymark.app.jpa.repository.OrderLineItemRepository;
import com.mymark.app.jpa.repository.OrderRepository;
import com.mymark.app.jpa.repository.ProductRepository;
import com.mymark.app.service.OrderService;
import com.mymark.app.service.ServiceException;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private CartLineItemRepository cartLineItemRepo;
	
	
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private InventoryItemRepository inventoryRepo;
	
	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderLineItemRepository orderLineItemRepo;
	
	
	protected final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Override
	public Order createOrder(Long customerId) throws ServiceException {
		Order order = null;
		
		Optional<Customer> c = customerRepo.findById(customerId);
		if (c.isPresent()) {			
			Customer customer = c.get();
			// Step 1: Create the empty Order
			order = orderRepo.save(new Order(customer, OrderStatus.NEW));
			customer = customerRepo.findById(customerId).get();
			// Step 2: Create order line items from the contents of the shopping cart
			Set<CartLineItem> cartItems = customer.getShoppingCart().getLineItems();
			for (CartLineItem item : cartItems) {
				addItemToOrder(order, item.getProduct(), item.getQuantity());
			}
			// Step 3: Remove the contents of the shopping cart
			cartLineItemRepo.deleteAllCartLineItemsFromShoppingCart(customer.getShoppingCart());
			order = orderRepo.findById(order.getId()).get();
		}
		
		return order;
	}

	@Override
	public Order getOrder(Long orderId) throws ServiceException {

		Order order = null;
		
		Optional<Order> optOrder = orderRepo.findById(orderId);
		if (optOrder.isPresent()) {
			order = optOrder.get();
		}
		return order;
	}

	@Override
	public Order submitOrder(Long orderId) throws ServiceException {
		Order order = null;
		
		Optional<Order> optOrder = orderRepo.findById(orderId);
		if (optOrder.isPresent()) {
			order = optOrder.get();			
			order.setStatus(OrderStatus.SUBMITTED);
			order = orderRepo.save(order);
			for (OrderLineItem oli : order.getOrderLineItems()) {
				Long inventoryId = oli.getInventoryItem().getId();
				Optional<InventoryItem> inventoryItemOpt = inventoryRepo.findById(inventoryId);
				if (inventoryItemOpt.isPresent()) {
					InventoryItem inventoryItem = inventoryItemOpt.get();
					inventoryItem.setStatus(InventoryStatus.SOLD);
					inventoryItem.setSellDate(new Date());
					inventoryItem = inventoryRepo.saveAndFlush(inventoryItem);
				}
			}

		}
		return order;
	}

	@Override
	public Order cancelOrder(Long orderId) throws ServiceException {
		Order order = null;
		
		Optional<Order> optOrder = orderRepo.findById(orderId);
		if (optOrder.isPresent()) {
			order = optOrder.get();
			// Step 1: Update the order status to CANCELLED
			order.setStatus(OrderStatus.CANCELLED);
			order = orderRepo.save(order);
			for (OrderLineItem oli : order.getOrderLineItems()) {
				// Step 2: Update the status of the inventory items back to AVAILABLE
				Long inventoryId = oli.getInventoryItem().getId();
				Optional<InventoryItem> inventoryItemOpt = inventoryRepo.findById(inventoryId);
				if (inventoryItemOpt.isPresent()) {
					InventoryItem inventoryItem = inventoryItemOpt.get();
					inventoryItem.setStatus(InventoryStatus.AVAILABLE);
					inventoryItem.setSellDate(null);
					inventoryItem = inventoryRepo.saveAndFlush(inventoryItem);
				}
			}
			// Step 3: Remove all order line items related to the order.
		    orderLineItemRepo.deleteOrderLineItemsByOrder(order); 
			order = orderRepo.findById(orderId).get();
		}
		
		return order;
	}
	

	@Override
	public void deleteAllOrdersForCustomer(Customer customer) throws ServiceException {
		Customer c = customerRepo.findByUserName(customer.getUserName());

		if (c != null) {
			List<Order> orders = orderRepo.findByCustomer(c);
			if (orders != null && !orders.isEmpty()) {
				for (Order o : orders) {
					Order order = orderRepo.getOne(o.getId());
					orderLineItemRepo.deleteOrderLineItemsByOrder(order);
				}				
			}
			
			orderRepo.deleteAllOrdersByCustomer(c);			
		}
		
	}


//	private void removeAllItemsFromOrder(Long orderId) throws ServiceException {
//		log.info("In OrderService.removeAllItemsFromOrder()");
//
//		Optional<Order> optOrder = orderRepo.findById(orderId);
//
//		if (optOrder.isPresent()) {
//			orderLineItemRepo.deleteOrderLineItemsByOrder(optOrder.get());
//			log.info("Deleted line items for order: " + orderId);
//		}
//	}

	private void addItemToOrder(Order o, Product p, Integer quantity) throws ServiceException {
		log.info("Calling addItemToOrder");
		Optional<Order> orderOpt = orderRepo.findById(o.getId());
		Optional<Product> prodOpt = productRepo.findById(p.getId());
		
		if (orderOpt.isPresent() && prodOpt.isPresent() && quantity !=null) {
			Order order = orderOpt.get();
			Product prod = prodOpt.get();
			Long inventoryCount = inventoryRepo.getAvailableInventoryCount(prod.getId());
			if (inventoryCount >= quantity) {
				List<Long> availInventoryList = inventoryRepo.getIdsForAllAvailableInventory(prod.getId());
				if (availInventoryList != null) {
					log.info("Fetch of all available inventory returned: " + availInventoryList.size() + " items.");
					availInventoryList = availInventoryList.subList(0, quantity);
					for (Long id : availInventoryList) {
						log.info("Adding " + id + " to order");
						Optional<InventoryItem> optInventory = inventoryRepo.findById(id);
						if (optInventory.isPresent()) {
							InventoryItem inventoryItem = optInventory.get();
							log.info(inventoryItem.getId() + ": " + inventoryItem.getStatus());
							OrderLineItem oli = new OrderLineItem();
							oli.setInventoryItem(inventoryItem);
							oli.setOrder(order);
							order.addOrderLineItem(oli);
							order = orderRepo.save(order);
						}
					}
				}
			} else {
				log.info("Not enough items are available to fulfill order.");
				// Not enough available inventory to fulfill the order.
			}
		}
		
	}
	
}
