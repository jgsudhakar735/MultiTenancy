package com.jgsudhakar.spring.multitenancy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.jgsudhakar.spring.multitenancy.filter.MultiTenancyContextFilter;

@SpringBootApplication
public class DataBasePerTenantApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataBasePerTenantApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<MultiTenancyContextFilter> securityValidateFilterRegistration() {
		FilterRegistrationBean<MultiTenancyContextFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(MultiTenancyContextFilter());
		registration.addUrlPatterns("*");
		registration.setName("MultiTenancyContextFilter");
		registration.setOrder(Integer.MAX_VALUE);
		return registration;
	}

	private MultiTenancyContextFilter MultiTenancyContextFilter() {
		return new MultiTenancyContextFilter();
	}
}
