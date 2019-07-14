package com.eksad.latihanrest.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	@Bean
	public Docket eksadAPI() {
		return new Docket (DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.eksad.latihanrest"))
				.paths(regex("/api.*"))// BasePath
				.build()
				.apiInfo(metaInfo())
				.tags(
						new Tag("Product", "Product Management API"),
						new Tag("Brand", "Brand Management API"),
						new Tag("Data Manipulation API", " "),
						new Tag("Get Data API", " "),
						new Tag("Admin", " "),
						new Tag("User", " ")
						);	
	}
	
	private ApiInfo metaInfo() {
		 ApiInfo apiInfo = new ApiInfo(
				"Employee Data Management REST API",//Title
				"Rest API Collecyion for Product Data Management", //Description 
				"1.0.0", //Version
				"http://google.com" , //termsOfServiceUrl 
				new Contact("Tri Eka Putra", "http://putratrieka.netlify.com", "putratrieka@gmail.com"),//contactName 
				"GitHub Licence",//license 
				"http://www.google.com/license", //licenseUrl)
				Collections.emptyList());// vendor extension
		 return apiInfo;
	}
}
