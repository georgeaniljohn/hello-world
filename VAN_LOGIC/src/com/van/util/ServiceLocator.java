package com.van.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.van.business.FacadeVanLogicManager;
import com.van.business.FacadeVanLogicManagerBean;
import com.van.business.VanRepositoryBean;


/**
 * 
 * @author George
 *
 */
public class ServiceLocator {
	
	
	/**
	 * 
	 */
	public ServiceLocator() {
	}

	/**
	 * 
	 * @return
	 */
	public FacadeVanLogicManager getVanLogicManager(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        "applicationContext.xml");
		FacadeVanLogicManager logicManager = (FacadeVanLogicManager)context.getBean("logicManagerBean");
		//VanRepositoryBean rp=(VanRepositoryBean)context.getBean("repositoryBean");
		//rp.getUserFromUsername("lukasp");
		//FacadeVanLogicManager logicManager = context.getBean(com.van.business.FacadeVanLogicManagerBean.class);
		return logicManager;
	}
	
}
