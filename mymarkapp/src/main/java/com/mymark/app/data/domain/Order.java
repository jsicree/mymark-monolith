package com.mymark.app.data.domain;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import com.mymark.app.data.enums.OrderStatus;

@Entity(name="ORDERS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Customer customer;

	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
	@OrderBy("id")
	private Set<OrderLineItem> orderLineItems = new LinkedHashSet<OrderLineItem>();

	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)	
	private OrderStatus status;

	@Column(name="ORDER_DATE")
	private Date orderDate;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Customer customer, OrderStatus status) {
		super();
		this.customer = customer;
		this.status = status;
		this.orderDate = new Date();
	}

	public Order(Customer customer, OrderStatus status, Date orderDate) {
		super();
		this.customer = customer;
		this.status = status;
		this.orderDate = orderDate;
	}
	
    public Order(Long id, Customer customer, OrderStatus status, Date orderDate) {
		super();
		this.id = id;
		this.customer = customer;
		this.status = status;
		this.orderDate = orderDate;
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Set<OrderLineItem> getOrderLineItems() {
		return orderLineItems;
	}

	public void setOrderLineItems(Set<OrderLineItem> lineItems) {
		this.orderLineItems = lineItems;
	}

	public void addOrderLineItem(OrderLineItem item) {
        orderLineItems.add(item);
        item.setOrder(this);
    }
 
    public void removeOrderLineItem(OrderLineItem item) {
        orderLineItems.remove(item);
        item.setOrder(null);
    }
    
	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [id=");
		builder.append(id);
		builder.append(", customer=");
		builder.append(customer.getId());
		builder.append(", status=");
		builder.append(status.name());
		builder.append(", orderDate=");
		builder.append(orderDate);
		builder.append(", orderLineItems=");
		builder.append(orderLineItems.size());
		builder.append("]");
		return builder.toString();
	}	
	
	
}
