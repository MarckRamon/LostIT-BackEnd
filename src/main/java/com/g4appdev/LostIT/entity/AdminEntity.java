package com.g4appdev.LostIT.entity;

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
    
    @Column(name = "FirstName", nullable = false)
    private String firstName;
    
    @Column(name = "LastName", nullable = false)
    private String lastName;
    
    @Column(name = "Username", nullable = false)
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "PhoneNumber", nullable = false)
    private String phoneNumber;
    
    @Lob
    @Column(name = "ProfilePicture", columnDefinition = "LONGBLOB")
    private byte[] profilePicture;
    
    @Column(name = "ProfilePictureType")
    private String profilePictureType;

    // Constructors, getters, and setters
    public AdminEntity() {}

    public AdminEntity(String email, String firstName, String lastName, 
                       String username, String password, String phoneNumber, 
                       byte[] profilePicture, String profilePictureType) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;      
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.profilePictureType = profilePictureType;
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
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getFirstName() {
        return firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getLastName() {
        return lastName;
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

	public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfilePictureType() {
        return profilePictureType;
    }

    public void setProfilePictureType(String profilePictureType) {
        this.profilePictureType = profilePictureType;
    }
 
 
    //public List<ItemEntity> getItems() {
    //    return items;
    //}
 
    //public void setItems(List<ItemEntity> items) {
    //    this.items = items;
    //}

	
}
