package com.van.business;

/*
 * FacadeVanLogicManagerBean.java			Jan 1, 2018
 *
 * All rights reserved
 *
 */
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.van.entity.EAOEntity;
import com.van.entity.UserEntity;

/**
 * This class acts in the service layer
 * @author George
 * 
 */
@Service("logicManagerBean")
@Transactional(propagation = Propagation.REQUIRED)
public class FacadeVanLogicManagerBean implements FacadeVanLogicManager {

	@Autowired
	private VanRepositoryBean repository;

	/**
	 * 
	 */
	public UserEntity getUserFromUsername(String username) {
		return repository.getUserFromUsername(username);

	}

	/**
	 * 
	 */
	public void updateUser(UserEntity user) {
		repository.updateUser(user);

	}

	/**
	 * 
	 * @return
	 */
	public Collection<UserEntity> getUserList() {
		return repository.getUserList();

	}

	/**
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(EAOEntity entity) {
		repository.create(entity);
	}
	
	/**
	 * 
	 */
	public List<?> findDynamicQuery(String query) {
		return repository.findDynamicQuery(query);
	}

}
