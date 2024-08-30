package com.uniqtech.profiles.SpringBootProfilesProd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class DevDataSourceConfig {

	@Value("${dev.datasource.url}")
	private String devDbUrl;

	@Value("${dev.datasource.username}")
	private String devDbUsername;

	@Value("${dev.datasource.password}")
	private String devDbPassword;

	@Bean
	public DataSource devDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(devDbUrl);
		dataSource.setUsername(devDbUsername);
		dataSource.setPassword(devDbPassword);
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return dataSource;
	}
}



