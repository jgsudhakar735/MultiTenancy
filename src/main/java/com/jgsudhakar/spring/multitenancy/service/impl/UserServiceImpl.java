package com.jgsudhakar.spring.multitenancy.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jgsudhakar.spring.multitenancy.dto.UserDto;
import com.jgsudhakar.spring.multitenancy.entity.UserEntity;
import com.jgsudhakar.spring.multitenancy.repository.UserRepository;
import com.jgsudhakar.spring.multitenancy.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public List<UserDto> fetchAll() {
		List<UserEntity> findAll = userRepository.findAll();
		
		return Optional.ofNullable(findAll).
				orElse(Collections.emptyList()).
				stream().
				map(entity -> {
					UserDto userDto = new UserDto();
					userDto.setId(entity.getId());
					userDto.setUserFirstName(entity.getUserFirstName());
					userDto.setUserLastName(entity.getUserLastName());
					userDto.setTenantId(entity.getTenantId());
					return userDto;
				}).
				collect(Collectors.toList());
	}
	
	@Override
	public List<UserDto> save(UserDto userDto) {
		UserEntity userEnt = new UserEntity();
		userEnt.setUserFirstName(userDto.getUserFirstName());
		userEnt.setUserLastName(userDto.getUserLastName());
		userEnt.setTenantId(userDto.getTenantId());
		userRepository.save(userEnt);
		return fetchAll();
	}

}
