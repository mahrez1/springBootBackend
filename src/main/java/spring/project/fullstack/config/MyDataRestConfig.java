 package spring.project.fullstack.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import spring.project.fullstack.entity.Country;
import spring.project.fullstack.entity.Product;
import spring.project.fullstack.entity.ProductCategory;
import spring.project.fullstack.entity.State;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	private EntityManager entityManager ;
	
	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager )
	{
		entityManager = theEntityManager ;
	}
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		// disable http for product and productCategory
		//RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
		HttpMethod[] theUnsupportedActions = {HttpMethod.DELETE , HttpMethod.PUT, HttpMethod.POST} ;
	
		    disableHttpMethods(Product.class, config, theUnsupportedActions);
			disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);
			disableHttpMethods(Country.class, config, theUnsupportedActions);
			disableHttpMethods(State.class, config, theUnsupportedActions);
			exposeIds(config) ;
	
	}
	private void disableHttpMethods(Class theClass , RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
		config.getExposureConfiguration()
		.forDomainType(theClass)
		.withItemExposure((metdata, httpMethods)-> httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metdata, httpMethods)-> httpMethods.disable(theUnsupportedActions)) ;
	}

	public void exposeIds(RepositoryRestConfiguration config)
	{
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities() ;
		List<Class> entityClasses = new ArrayList<>() ;
		for (EntityType tempEntityType : entities)
		{
			entityClasses.add(tempEntityType.getJavaType()) ;
		}
		
		Class[] domainTypes = 	entityClasses.toArray(new Class[0]) ;
		config.exposeIdsFor(domainTypes) ;
		
	}
	
	
	
	
}
