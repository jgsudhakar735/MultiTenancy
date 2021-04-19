package com.jgsudhakar.spring.multitenancy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jgsudhakar.spring.multitenancy.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>,PagingAndSortingRepository<UserEntity, Long>{

}
