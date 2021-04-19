/**
 * 
 */
package com.jgsudhakar.spring.multitenancy.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.jgsudhakar.spring.multitenancy.util.MultiTenancyConstant;

import lombok.extern.log4j.Log4j2;

/**
 * @author sudhakar.t
 *
 */
@Log4j2
public class MultiTenancyContextFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("----------------- Request Coming here -----------------"+request.getRequestURI());
		try {
			HeaderMapRequestWrapper wrappedRequest = new HeaderMapRequestWrapper((HttpServletRequest)request);
			String tenant = wrappedRequest.getHeader(MultiTenancyConstant.TENANT_HEADER_NAME);
			MultiTenancyContext.setCurrentTenant(tenant);
			filterChain.doFilter(request, response);
			log.info("----------------- Response Sernding here -----------------"+request.getRequestURI());
		}catch (Exception e) {
			
		}finally {
			MultiTenancyContext.clear();
		}
	}

}
