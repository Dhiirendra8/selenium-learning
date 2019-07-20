package net.codejava.spring.config;

import javax.sql.DataSource;

import net.codejava.spring.dao.EnvDetailDAO;
import net.codejava.spring.dao.EnvDetailDAOImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.codejava.spring.utils.Constants;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.context.MessageSource;

@Configuration
// @ComponentScan(basePackages={"net.codejava.spring.controller","net.codejava.spring.validator"})
@ComponentScan(basePackages  = {"net.codejava.spring","com.codejava.spring"})
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages");
		return source;
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		
		
		dataSource.setDriverClassName(Constants.driver);
		dataSource.setUrl(Constants.url);
		dataSource.setUsername(Constants.username);
		dataSource.setPassword(Constants.password);
		
		return dataSource;
	}

	@Bean
	public EnvDetailDAO getContactDAO() {
		return new EnvDetailDAOImpl(getDataSource());
	}
}
