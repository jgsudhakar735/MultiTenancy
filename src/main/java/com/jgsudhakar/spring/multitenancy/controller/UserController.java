package com.jgsudhakar.spring.multitenancy.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgsudhakar.spring.multitenancy.dto.UserDto;
import com.jgsudhakar.spring.multitenancy.service.UserService;
import com.jgsudhakar.spring.multitenancy.util.MultiTenancyConstant;

@RestController
@RequestMapping("user")
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService= userService;
	}
	
	@GetMapping("/")
	public List<UserDto> retrieveUsers(@RequestHeader(MultiTenancyConstant.TENANT_HEADER_NAME) String tenantName) {
		return userService.fetchAll();
	}

	@PostMapping("/")
	public List<UserDto> saveUser(@RequestHeader(MultiTenancyConstant.TENANT_HEADER_NAME) String tenantName, @RequestBody UserDto userDto) {
		userDto.setTenantId(tenantName);
		return userService.save(userDto);
	}
}
