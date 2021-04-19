package com.jgsudhakar.spring.multitenancy.service;

import java.util.List;

import com.jgsudhakar.spring.multitenancy.dto.UserDto;

public interface UserService {

	public List<UserDto> fetchAll();

	public List<UserDto> save(UserDto userDto);
}
