package com.ashar.MyClassroom;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import javax.sql.DataSource;
import org.springframework.boot.CommandLineRunner;



@SpringBootApplication
@EnableEncryptableProperties
public class MyClassroomApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MyClassroomApplication.class, args);
	}
	@Autowired
	private DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {
		String sqlQuery = "call CreateTables();";
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) dataSource);
		jdbcTemplate.execute(sqlQuery);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}

