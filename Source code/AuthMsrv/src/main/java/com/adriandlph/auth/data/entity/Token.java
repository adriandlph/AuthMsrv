package com.adriandlph.auth.data.entity;

import com.adriandlph.auth.data.enumeration.TokenType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 *
 * @author adriandlph
 * 
 */
@Entity
@Table(name = "token")
public class Token {
	private static final int TOKEN_LENGTH = 2048;
	
	// Primary Keys
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	protected Long id;
	// Columns
	@Column(name = "token", nullable = false, length = TOKEN_LENGTH)
	protected String token;
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	protected TokenType type;
	// Foreign keys
	@JoinColumn(name = "userId", nullable = false, unique = false, referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	protected User user;

	public Token() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenType getType() {
		return type;
	}

	public void setType(TokenType type) {
		this.type = type;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 71 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		if (!(obj instanceof Token other)) return false;
		
		if (!Objects.equals(this.id, other.id)) 		return false;
		if (!Objects.equals(this.token, other.token))	return false;
		if (this.type != other.type)					return false;
		return Objects.equals(this.user, other.user);
	}

	@Override
	public String toString() {
		StringBuffer str;
		
		str = new StringBuffer("Token{");
		str.append(" id=");
		str.append(id);
		str.append(", token=");
		str.append(token);
		str.append(", type=");
		str.append(type.name());
		str.append(", user=");
		str.append(user.toString());
		
		return str.toString();
	}
	
}
