package com.mymark.app.data;

import com.mymark.api.CustomerDto;
import com.mymark.app.data.domain.Customer;

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
	
}
