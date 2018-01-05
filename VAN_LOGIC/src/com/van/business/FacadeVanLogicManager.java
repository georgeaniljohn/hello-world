package com.van.business;

/*
 * FacadeVanLogicManager.java			Jan 1, 2018
 *
 * All rights reserved
 *
 */

import java.util.Collection;
import java.util.List;
import com.van.entity.EAOEntity;
import com.van.entity.UserEntity;

/**
 * 
 * @author George
 * 
 */
public interface FacadeVanLogicManager {
	/**
	 * 
	 * @param username
	 * @return
	 */
	public UserEntity getUserFromUsername(String username);

	/**
	 * 
	 * @param user
	 */
	public void updateUser(UserEntity user);

	/**
	 * 
	 * @return
	 */
	public Collection<UserEntity> getUserList();

	/**
	 * 
	 * @param entity
	 */
	public void create(EAOEntity entity);

	/**
	 * 
	 * @param query
	 * @return
	 */
	public List<?> findDynamicQuery(String query);
}
