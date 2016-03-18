package com.marse.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

	private static SessionFactory factory=null;
	private static ServiceRegistry serviceRegistry;
	
	private HibernateUtils(){
		
	}
	public static SessionFactory getInstance(){
		
		if (factory==null) {
			
			//factory=new Configuration().configure("com/marse/hibernate/config/hibernate.cfg.xml").buildSessionFactory();
			
			Configuration configuration = new Configuration();
		    configuration.configure("com/marse/hibernate/config/hibernate.cfg.xml");
		    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    factory = configuration.buildSessionFactory(serviceRegistry);
		}
		
		return factory;
	}
	
}
