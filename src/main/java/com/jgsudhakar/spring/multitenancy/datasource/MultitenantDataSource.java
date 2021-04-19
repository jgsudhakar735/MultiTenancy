package com.jgsudhakar.spring.multitenancy.datasource;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.jgsudhakar.spring.multitenancy.filter.MultiTenancyContext;

public class MultitenantDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return MultiTenancyContext.getCurrentTenant();
	}

	@Override
	protected DataSource determineTargetDataSource() {
		System.out.println(":: CURRENT TENANT ::"+determineCurrentLookupKey());
        return getResolvedDataSources().getOrDefault(determineCurrentLookupKey(),getResolvedDefaultDataSource());
	}

}
