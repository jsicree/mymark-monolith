package com.mymark.app.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mymark.api.CartLineItemDto;
import com.mymark.api.CartLineItemDtoList;
import com.mymark.api.CustomerDto;
import com.mymark.api.OrderDto;
import com.mymark.api.OrderLineItemDto;
import com.mymark.api.OrderLineItemDtoList;
import com.mymark.api.ProductDetailsDto;
import com.mymark.api.ProductDto;
import com.mymark.api.ShoppingCartDto;
import com.mymark.app.data.domain.CartLineItem;
import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.domain.Order;
import com.mymark.app.data.domain.OrderLineItem;
import com.mymark.app.data.domain.Product;
import com.mymark.app.data.domain.ShoppingCart;

public final class DTOConverter {

		public static CustomerDto toCustomerDto(Customer c) {
			CustomerDto dto = new CustomerDto();
			dto.setId(c.getId());
			dto.setFirstName(c.getFirstName());
			dto.setLastName(c.getLastName());
			dto.setUserName(c.getUserName());
			dto.setEmail(c.getEmail());
			return dto;
		}

		public static ProductDto toProductDto(Product p) {
			ProductDto dto = new ProductDto();
			dto.setId(p.getId());
			dto.setName(p.getName());
			dto.setProductCode(p.getProductCode());
			dto.setPrice(p.getPrice());
			dto.setShortDescription(p.getShortDescription());
			return dto;
		}

		public static List<ProductDto> toProductDtoList(ArrayList<Product> prodList) {
			
			List<ProductDto> dtoList = new ArrayList<ProductDto>();
			
			for (Product p : prodList) {
				dtoList.add(toProductDto(p));
			}
			
			return dtoList;
		}

		public static ProductDetailsDto toProductDetailsDto(Product p, Long count) {
			ProductDetailsDto dto = new ProductDetailsDto();
			dto.setId(p.getId());
			dto.setName(p.getName());
			dto.setProductCode(p.getProductCode());
			dto.setPrice(p.getPrice());
			dto.setShortDescription(p.getShortDescription());
			dto.setLongDescription(p.getLongDescription());
			dto.setAvailableInventory(count);
			return dto;
		}

		public static CartLineItemDto toCartLineItemDto(CartLineItem lineItem) {
			CartLineItemDto dto = new CartLineItemDto();

			dto.setId(lineItem.getId());
			dto.setQuantity(lineItem.getQuantity());
			Double linePrice = lineItem.getQuantity() * lineItem.getProduct().getPrice();			
			dto.setLinePrice(linePrice);
			dto.setProduct(toProductDto(lineItem.getProduct()));
			
			return dto;
		}
				
		public static CartLineItemDtoList toCartLineItemDtoList(Set<CartLineItem> lineItems) {

			CartLineItemDtoList dtoList = new CartLineItemDtoList();

			if (lineItems != null && !lineItems.isEmpty()) {
				for (CartLineItem i : lineItems) {
					dtoList.getLineItems().add(toCartLineItemDto(i));					
				}				
			}
			
			return dtoList;
		}	

		public static ShoppingCartDto toShoppingCartDto(ShoppingCart cart) {
			ShoppingCartDto dto = new ShoppingCartDto();
			dto.setId(cart.getId());
			dto.setUserName(cart.getCustomer().getUserName());
			CartLineItemDtoList itemList = toCartLineItemDtoList(cart.getLineItems());
			dto.setLineItems(itemList);

			Long totalQuantity = 0L;
			Double totalPrice = 0.0;
			
			for (CartLineItemDto item : itemList.getLineItems()) {
				totalQuantity += item.getQuantity();
				totalPrice += item.getLinePrice();				
			}
			dto.setTotalPrice(totalPrice.doubleValue());
			dto.setNumLineItems(itemList.getLineItems().size());
			dto.setTotalQuantity(totalQuantity);
			return dto;
		}	

		public static OrderLineItemDto toOrderLineItemDto(OrderLineItem lineItem) {
			OrderLineItemDto dto = new OrderLineItemDto();

			dto.setId(lineItem.getId());
			dto.setInventoryId(lineItem.getInventoryItem().getId());
			dto.setLinePrice(lineItem.getInventoryItem().getProduct().getPrice());
			dto.setProductCode(lineItem.getInventoryItem().getProduct().getProductCode());
			return dto;
		}

		public static OrderLineItemDtoList toOrderLineItemDtoList(Set<OrderLineItem> lineItems) {

			OrderLineItemDtoList dtoList = new OrderLineItemDtoList();

			if (lineItems != null && !lineItems.isEmpty()) {
				for (OrderLineItem i : lineItems) {
					dtoList.getLineItems().add(toOrderLineItemDto(i));					
				}				
			}
			
			return dtoList;
		}	
		
		public static OrderDto toOrderDto(Order order) {
			OrderDto dto = new OrderDto();

			dto.setId(order.getId());
			dto.setStatus(order.getStatus().name());
			dto.setUserName(order.getCustomer().getUserName());
			OrderLineItemDtoList itemList = toOrderLineItemDtoList(order.getOrderLineItems());
			dto.setLineItems(itemList);

			
			Long totalQuantity = 0L;
			Double totalPrice = 0.0;
			
			for (OrderLineItemDto item : itemList.getLineItems()) {
				totalQuantity++;
				totalPrice += item.getLinePrice();				
			}
			dto.setTotalPrice(totalPrice);
			dto.setTotalQuantity(totalQuantity);
			dto.setNumLineItems(totalQuantity.intValue());
			dto.setNumLineItems(order.getOrderLineItems().size());
			return dto;
		}
		
}
