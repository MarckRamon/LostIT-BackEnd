package com.g4appdev.LostIT.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name="claim")
public class ClaimEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClaimID")
    private int claimId;
    
    @Column(name = "studEmail", nullable = false)
    private String studEmail;
    
    @Column(name = "FirstName", nullable = false)
    private String firstName;
    
    @Column(name = "LastName", nullable = false)
    private String lastName;
    
    @Column(name = "dateClaimed", nullable = false)
    private String dateClaimed;
 
    @OneToOne(mappedBy = "claim",  fetch = FetchType.LAZY)
    @JsonManagedReference
    private ItemEntity item;
    
   // @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@JsonManagedReference // This ensures that items will be properly serialized when fetching the admin
    //private List<ItemEntity> items;
 
    public ClaimEntity() {}
 
    public ClaimEntity(String studEmail, String firstName, String lastName, String dateClaimed) {
        this.studEmail = studEmail;
        this.firstName = firstName;
        this.lastName = lastName;      
        this.dateClaimed = dateClaimed;
    }
 
    public int getClaimId() {
        return claimId;
    }
    
    public void setStudEmail(String studEmail) {
    	this.studEmail = studEmail;
    }
 
    public String getStudEmail() {
        return studEmail;
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
    
    public void setDateClaimed(String dateClaimed) {
        this.dateClaimed = dateClaimed;
    }
 
    public String getDateClaimed() {
        return dateClaimed;
    }
    
    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }
    

	
}
