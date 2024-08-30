package com.uniqtech.profiles.SpringBootProfilesProd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class ProdDataSourceConfig {

	@Value("${prod.datasource.url}")
	private String prodDbUrl;

	@Value("${prod.datasource.username}")
	private String prodDbUsername;

	@Value("${prod.datasource.password}")
	private String prodDbPassword;

	@Bean
	public DataSource prodDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(prodDbUrl);
		dataSource.setUsername(prodDbUsername);
		dataSource.setPassword(prodDbPassword);
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return dataSource;
	}
}
