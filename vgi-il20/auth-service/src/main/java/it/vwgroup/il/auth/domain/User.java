package it.vwgroup.il.auth.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name = "users")
public class User implements Serializable {
/*
	@Id
	@Column(name="id", unique=true)
	private int id;
*/	
	@Id
	@Column(name="username", unique=true)
	private String username;

	private String password;

/*	
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
*/
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
