package com.whck.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	@Bean(destroyMethod = "close")
	public BasicDataSource dataSource(Properties dataSourceInfo) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(dataSourceInfo.getProperty("url"));
		dataSource.setUsername(dataSourceInfo.getProperty("username"));
		dataSource.setDriverClassName(dataSourceInfo.getProperty("driverClassName"));
		dataSource.setPassword(dataSourceInfo.getProperty("password"));
		return dataSource;
	}

}
