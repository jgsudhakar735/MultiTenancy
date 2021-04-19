package com.jgsudhakar.spring.multitenancy.filter;

public class MultiTenancyContext {

	private static final InheritableThreadLocal<Object> currentTenant = new InheritableThreadLocal<>();
	
	// private constructor.
	private MultiTenancyContext() {
		
	}

    public static void setCurrentTenant(Object tenant) {
        currentTenant.set(tenant);
    }

    public static Object getCurrentTenant() {
        return currentTenant.get();
    }
    
    public static void clear() {
    	currentTenant.remove();
	}
}
