package spring.project.fullstack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import spring.project.fullstack.entity.Product;
import spring.project.fullstack.entity.ProductCategory;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		// disable http for product and productCategory
		//RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
		HttpMethod[] theUnsupportedActions = {HttpMethod.DELETE , HttpMethod.PUT, HttpMethod.POST} ;
		config.getExposureConfiguration()
		.forDomainType(Product.class)
		.withItemExposure((metdata, httpMethods)-> httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metdata, httpMethods)-> httpMethods.disable(theUnsupportedActions)) ;
		
			config.getExposureConfiguration()
			.forDomainType(ProductCategory.class)
			.withItemExposure((metdata, httpMethods)-> httpMethods.disable(theUnsupportedActions))
			.withCollectionExposure((metdata, httpMethods)-> httpMethods.disable(theUnsupportedActions)) ;
	}

}
