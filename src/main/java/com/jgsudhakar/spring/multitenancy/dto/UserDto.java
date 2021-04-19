package com.jgsudhakar.spring.multitenancy.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable{

	/**
	 * Default Serial id
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String userFirstName;
	
	private String userLastName;
	
	private String tenantId;
}
