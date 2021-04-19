package com.jgsudhakar.spring.multitenancy.datasource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class MultitenantConfiguration {

    @Autowired
    private DataSourceProperties properties;

    /**
     * Defines the data source for the application
     * @return
     */
    @Bean
    @ConfigurationProperties(
            prefix = "spring.datasource"
    )
    public DataSource dataSource() {
        File[] files = Paths.get("D:\\sudhakar\\softwares\\SpringSTS\\sts-4.1.0.RELEASE\\admingw_D7.1_release\\MultiTenancy\\src\\main\\resources\\tenant\\").toFile().listFiles();
        Map<Object,Object> resolvedDataSources = new HashMap<>();

        for(File propertyFile : files) {
            Properties tenantProperties = new Properties();
            DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create(this.getClass().getClassLoader());

            try {
                tenantProperties.load(new FileInputStream(propertyFile));

                String tenantId = tenantProperties.getProperty("name");

                // Assumption: The tenant database uses the same driver class
                // as the default database that you configure.
                dataSourceBuilder.driverClassName(properties.getDriverClassName())
                        .url(tenantProperties.getProperty("datasource.jdbcUrl"))
                        .username(tenantProperties.getProperty("datasource.username"))
                        .password(tenantProperties.getProperty("datasource.password"));

                if(properties.getType() != null) {
                    dataSourceBuilder.type(properties.getType());
                }

                resolvedDataSources.put(tenantId, dataSourceBuilder.build());
                
                System.out.println(":: Added Two Data Sources ::"+resolvedDataSources.size());
            } catch (IOException e) {
                log.error(":: Could Not find the Tenant : Better to Shutdown ::");
                return null;
            }
        }

        // Create the final multi-tenant source.
        // It needs a default database to connect to.
        // Make sure that the default database is actually an empty tenant database.
        // Don't use that for a regular tenant if you want things to be safe!
        MultitenantDataSource dataSource = new MultitenantDataSource();
        dataSource.setDefaultTargetDataSource(defaultDataSource());
        dataSource.setTargetDataSources(resolvedDataSources);

        // Call this to finalize the initialization of the data source.
        dataSource.afterPropertiesSet();

        return dataSource;
    }

    /**
     * Creates the default data source for the application
     * @return
     */
    private DataSource defaultDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create(this.getClass().getClassLoader())
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword());

        if(properties.getType() != null) {
            dataSourceBuilder.type(properties.getType());
        }

        return dataSourceBuilder.build();
    }
}
