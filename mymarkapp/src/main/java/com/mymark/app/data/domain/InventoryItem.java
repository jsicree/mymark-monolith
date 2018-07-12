package com.mymark.app.data.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mymark.app.data.enums.InventoryStatus;

@Entity(name="INVENTORY")
public class InventoryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional=false)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	@Column(name="STATUS", nullable = false)
	@Enumerated(EnumType.STRING)	
	private InventoryStatus status;

	@Column(name="SELL_DATE")
	private Date sellDate;
	
	@Column(name="IN_PROCESS_DATE")
	private Date inProcessDate;

	@Column(name="RETURN_DATE")
	private Date returnDate;

	public InventoryItem() {
		super();
	}

	public InventoryItem(Long id, Product product, InventoryStatus status, Date sellDate, Date inProcessDate,
			Date returnDate) {
		super();
		this.id = id;
		this.product = product;
		this.status = status;
		this.sellDate = sellDate;
		this.inProcessDate = inProcessDate;
		this.returnDate = returnDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public InventoryStatus getStatus() {
		return status;
	}

	public void setStatus(InventoryStatus status) {
		this.status = status;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public Date getInProcessDate() {
		return inProcessDate;
	}

	public void setInProcessDate(Date inProcessDate) {
		this.inProcessDate = inProcessDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inProcessDate == null) ? 0 : inProcessDate.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + ((sellDate == null) ? 0 : sellDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		InventoryItem other = (InventoryItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inProcessDate == null) {
			if (other.inProcessDate != null)
				return false;
		} else if (!inProcessDate.equals(other.inProcessDate))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		if (sellDate == null) {
			if (other.sellDate != null)
				return false;
		} else if (!sellDate.equals(other.sellDate))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InventoryItem [id=");
		builder.append(id);
		builder.append(", product=");
		builder.append(product);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sellDate=");
		builder.append(sellDate);
		builder.append(", inProcessDate=");
		builder.append(inProcessDate);
		builder.append(", returnDate=");
		builder.append(returnDate);
		builder.append("]");
		return builder.toString();
	}

	
}
