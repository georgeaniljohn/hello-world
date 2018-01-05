package com.van.business;

/*
 * VanRepositoryBean.java			Jan 1, 2018
 *
 * All rights reserved
 *
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.van.entity.EAOEntity;
import com.van.entity.UserEntity;
import com.van.util.DbConnector;

/**
 * This is the repository class that acts in the DAO layer
 * 
 * @author George
 * 
 */
@Repository("repositoryBean")
@PersistenceUnit(name = "VAN_LOGIC_UNIT")
public class VanRepositoryBean {

	@PersistenceContext
	private EntityManager em;

	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		System.out.println("VanRepositoryBean is initialized");
	}

	/**
	 * 
	 * @param entity
	 */
	public void create(EAOEntity entity) {
		em.persist(entity);
	}
	
	public void update(EAOEntity entity) {
		em.merge(entity);
	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	public List<?> findDynamicQuery(String query) {
		return em.createQuery(query).getResultList();
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	public UserEntity getUserFromUsername(String username) {
		String sqlQuery = "SELECT * FROM USER user WHERE user.USERNAME=?";
		UserEntity user = null;
		Long id = null;
		Long age = null;
		String name = "";
		String password = "";

		try {
			DbConnector dbConnector = new DbConnector();
			Connection connector = dbConnector.getConnection();
			PreparedStatement ps = connector.prepareStatement(sqlQuery);
			ps.setString(1, username);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				user = new UserEntity();
				id = new Long(resultSet.getLong(1));
				name = resultSet.getString(2);
				age = new Long(resultSet.getLong(3));
				password = resultSet.getString(5);
				user.setId(id);
				user.setName(name);
				user.setAge(age);
				user.setUsername(username);
				user.setPassword(password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

	/**
	 * 
	 * @param user
	 */
	public void updateUser(UserEntity user) {
		String sqlQuery = "UPDATE USER user SET user.LAST_LOGIN=?,user.NAME=?,user.AGE=?,user.PASSWORD=? WHERE user.USERNAME=?";

		try {
			DbConnector dbConnector = new DbConnector();
			Connection connector = dbConnector.getConnection();
			PreparedStatement ps = connector.prepareStatement(sqlQuery);
			ps.setString(5, user.getUsername());
			user.setLastLogin(new Date());
			ps.setTimestamp(1, new java.sql.Timestamp(user.getLastLogin().getTime()));
			ps.setString(2, user.getName());
			ps.setLong(3, user.getAge().longValue());
			ps.setString(4, user.getPassword());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return
	 */
	public Collection<UserEntity> getUserList() {
		String sqlQuery = "SELECT * FROM USER user";
		UserEntity user = null;
		Long id = null;
		Long age = null;
		String name = "";
		String username = "";
		String password = "";
		Collection<UserEntity> userList = new ArrayList<UserEntity>();

		try {
			DbConnector dbConnector = new DbConnector();
			Connection connector = dbConnector.getConnection();
			PreparedStatement ps = connector.prepareStatement(sqlQuery);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				user = new UserEntity();
				id = new Long(resultSet.getLong(1));
				name = resultSet.getString(2);
				age = new Long(resultSet.getLong(3));
				username = resultSet.getString(4);
				password = resultSet.getString(5);
				user.setId(id);
				user.setName(name);
				user.setAge(age);
				user.setUsername(username);
				user.setPassword(password);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;

	}

}
