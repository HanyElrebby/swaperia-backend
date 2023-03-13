package com.swaperia.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "user", schema = "public")
public class User extends AbstractAuditingEntity implements Serializable {	

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
    
	@NotNull
    @Size(max = 100)
	@Column(name = "username")
    private String username;
    
	@NotNull
    @Size(max = 254)
	@Column(name = "email")
    private String email;
    
    @NotNull
	@Size(min = 6, max = 60)
	@Column(name = "password")
	@JsonIgnore
    private String password;
    
    private String mobileNumber;
    
    private Date dateOfBirth;
    
    @NotNull
	@Column(name = "activated")
    private boolean activated = false;
	   
	@Column(name = "activation_key")
    @JsonIgnore
    private String activationKey;
    
	@Column(name = "lang_key")
	private String langKey;
	
	@Column(name = "image_url")
	private String imageUrl;
    
	@Column(name = "reset_key")
    @JsonIgnore
    private String resetKey;
    
	@Column(name = "reset_date")
    private Instant resetDate = null;
    
	@ManyToMany
	@JoinTable(
			name = "user_authority",
			joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
			inverseJoinColumns = { @JoinColumn(name = "authority_name", referencedColumnName = "name") }
	)
	@JsonIgnore
    private Set<Authority> authorities = new HashSet<Authority>();

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
	private List<Product> products = new ArrayList<>();
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getLangKey() {
		return langKey;
	}

	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getResetKey() {
		return resetKey;
	}

	public void setResetKey(String resetKey) {
		this.resetKey = resetKey;
	}

	public Instant getResetDate() {
		return resetDate;
	}

	public void setResetDate(Instant resetDate) {
		this.resetDate = resetDate;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
	

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", activated=" + activated + ", activationKey=" + activationKey + ", langKey=" + langKey
				+ ", imageUrl=" + imageUrl + ", resetKey=" + resetKey + ", resetDate=" + resetDate + ", authorities="
				+ authorities + "]";
	}
}
