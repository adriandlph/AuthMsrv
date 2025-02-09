package com.adriandlph.auth.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 *
 * @author adriandlph
 * 
 */
@Entity
@Table(name = "user")
public class User {
	
	// Primary Keys
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	protected Long id;
	// Columns
	@Column(name = "username", nullable = false)
	protected String username;
	@Column(name = "password", nullable = false)
	protected String password;
	@Column(name = "email", nullable = false)
	protected String email;
	@Column(name = "name", nullable = true)
	protected String name;
	@Column(name = "last_name", nullable = true)
	protected String lastName;
	// Foreign keys

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 17 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		if (!(obj instanceof User other)) return false;
		
		if (!Objects.equals(this.username, other.username)) return false;
		if (!Objects.equals(this.password, other.password)) return false;
		if (!Objects.equals(this.email, other.email))		return false;
		if (!Objects.equals(this.name, other.name)) 		return false;
		if (!Objects.equals(this.lastName, other.lastName)) return false;
		
		return Objects.equals(this.id, other.id);
	}

	@Override
	public String toString() {
		StringBuffer str;
		
		str = new StringBuffer("User{");
		str.append(" id=");
		str.append(id.toString());
		str.append(", username=");
		str.append(username);
		// str.append(", password=");
		// str.append(password);
		str.append(", email=");
		str.append(email);
		str.append(", name=");
		str.append(name);
		str.append(", lastName=");
		str.append(lastName);
		str.append(" }");
		
		return str.toString();
	}
	
	
	
	
	
}
