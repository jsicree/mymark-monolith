package com.mymark.app.data;

import java.util.ArrayList;
import java.util.List;

import com.mymark.api.CustomerDto;
import com.mymark.api.ProductDetailsDto;
import com.mymark.api.ProductDto;
import com.mymark.api.ShoppingCartDto;
import com.mymark.app.data.domain.Customer;
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

		public static ShoppingCartDto toShoppingCartDto(ShoppingCart cart) {
			ShoppingCartDto dto = new ShoppingCartDto();
			dto.setId(cart.getId());
			dto.setUserName(cart.getCustomer().getUserName());
			dto.setTotalPrice(new Double(0.0));
			return dto;
		}	
	
}
