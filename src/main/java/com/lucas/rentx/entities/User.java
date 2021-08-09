package com.lucas.rentx.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucas.rentx.entities.enums.Perfil;

@Entity
@Table(name = "tb_users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private UUID id;

	private String name;

	@Column(unique = true)
	private String username;

	@JsonIgnore
	private String password;

	@Column(unique = true)
	private String email;

	@Column(name = "driver_license")
	private String driverLicense;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	private String avatar;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Rental> rentals = new ArrayList<>();

	@OneToMany(mappedBy = "tokenUser", fetch = FetchType.LAZY)
	private List<UserToken> tokens = new ArrayList<>();

	@PrePersist
	private void prePersist() {
		this.createdAt = LocalDateTime.now();
	}

	public User() {
		addPerfil(Perfil.USER);
	}

	public User(UUID id, String name, String username, String password, String email, String driverLicense,
			String avatar, LocalDateTime createAt) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.driverLicense = driverLicense;
		this.avatar = avatar;
		this.createdAt = createAt;
		addPerfil(Perfil.USER);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createAt) {
		this.createdAt = createAt;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public List<UserToken> getTokens() {
		return tokens;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
