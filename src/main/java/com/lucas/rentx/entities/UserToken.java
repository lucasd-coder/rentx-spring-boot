package com.lucas.rentx.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_users_tokens")
public class UserToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "refresh_token")
	private UUID refreshToken;

	@Column(name = "expires_date")
	private Date expiresDate;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User tokenUser;

	@PrePersist
	private void prePersist() {
		this.createdAt = LocalDateTime.now();
	}

	public UserToken() {
	}

	public UserToken(UUID id, UUID refreshToken, Date expiresDate, LocalDateTime createdAt, User tokenUser) {
		super();
		this.id = id;
		this.refreshToken = refreshToken;
		this.expiresDate = expiresDate;
		this.createdAt = createdAt;
		this.tokenUser = tokenUser;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(UUID refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Date getExpiresDate() {
		return expiresDate;
	}

	public void setExpiresDate(Date expiresDate) {
		this.expiresDate = expiresDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getTokenUser() {
		return tokenUser;
	}

	public void setTokenUser(User tokenUser) {
		this.tokenUser = tokenUser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserToken other = (UserToken) obj;
		return Objects.equals(id, other.id);
	}

}
