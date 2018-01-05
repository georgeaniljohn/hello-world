package com.van.entity;
/*
 * UserEntity.java			Jan 1, 2018
 *
 * All rights reserved
 *
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The entity POJO class for the table USER
 * @author George
 * 
 */
@Entity
@Table(name = "USER")
public class UserEntity implements EAOEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5298305589504094531L;
	private Long id;
	private String username;
	private String password;
	private String name;
	private Long age;
	private Date lastLogin;

	/**
	 * 
	 */
	public UserEntity() {
	}

	/**
	 * 
	 * @param id
	 * @param username
	 * @param password
	 * @param name
	 * @param age
	 * @param lastLogin
	 */
	public UserEntity(Long id, String username, String password, String name, Long age, Date lastLogin) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.age = age;
		this.lastLogin = lastLogin;
	}

	/**
	 * 
	 * @return
	 */
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "AGE")
	public Long getAge() {
		return age;
	}

	/**
	 * 
	 * @param age
	 */
	public void setAge(Long age) {
		this.age = age;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "LAST_LOGIN")
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * 
	 * @param lastLogin
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "UserEntity [age=" + age + ", id=" + id + ", lastLogin=" + lastLogin + ", name=" + name + ", password="
				+ password + ", username=" + username + "]";
	}

}
