package com.mymark.app.data.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name="ORDER_LINE_ITEM")
public class OrderLineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ORDER_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Order order;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_item_id")
	private InventoryItem inventoryItem;

	public OrderLineItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderLineItem(Long id, Order order, InventoryItem inventoryItem) {
		super();
		this.id = id;
		this.order = order;
		this.inventoryItem = inventoryItem;
	}

	public OrderLineItem(Order order, InventoryItem inventoryItem) {
		super();
		this.order = order;
		this.inventoryItem = inventoryItem;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}

	public void setInventoryItem(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((inventoryItem == null) ? 0 : inventoryItem.hashCode());
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
		OrderLineItem other = (OrderLineItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (inventoryItem == null) {
			if (other.inventoryItem != null)
				return false;
		} else if (!inventoryItem.equals(other.inventoryItem))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CartLineItem [id=");
		builder.append(id);
		builder.append(", order=");
		builder.append(order.getId());
		builder.append(", inventoryItem=");
		builder.append(inventoryItem.getId());
		builder.append("]");
		return builder.toString();
	}
	
}
