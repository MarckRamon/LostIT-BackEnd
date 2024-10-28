package com.g4appdev.LostIT.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;


@Entity
@Table(name = "admin")
public class AdminEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AdminID")
    private int adminId;
    
    @Column(name = "Email", nullable = false)
    private String email;
    
    @Column(name = "FullName", nullable = false)
    private String fullName;
    
    @Column(name = "Username", nullable = false)
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "PhoneNumber", nullable = false)
    private String phoneNumber;
 
   
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference // This ensures that items will be properly serialized when fetching the admin
    private List<ItemEntity> items;
 
    public AdminEntity() {}
 
    public AdminEntity(String email, String fullName, String username, String password, String phoneNumber) {
        this.email = email;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
 
    public int getAdminId() {
        return adminId;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
 
    public String getFullName() {
        return fullName;
    }
    

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
 
 
    public List<ItemEntity> getItems() {
        return items;
    }
 
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

	
}
