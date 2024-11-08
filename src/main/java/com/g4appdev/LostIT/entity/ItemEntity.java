package com.g4appdev.LostIT.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import com.fasterxml.jackson.annotation.JsonBackReference;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class ItemEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ItemID")
    private int itemId;
 
    @Column(name = "ItemName")
    private String itemName;
 
    @Column(name = "Description")
    private String description;
    
    @Column(name = "ItemStatus")
    private String status;
    
    @Column(name = "Date")
    private String date;
    
    
    //@ManyToOne
    //@JoinColumn(name = "AdminID") 
    //@JsonBackReference
    //private AdminEntity admin;
    
    
    @ManyToOne
    @JoinColumn(name = "CategoryID")
    //@JsonBackReference
    @JsonIgnoreProperties("items")
    private CategoryEntity category;
    
    
    @ManyToOne
    @JoinColumn(name = "LocationID") 
    //@JsonBackReference
    @JsonIgnoreProperties("items")
    private LocationEntity location;
 
    public ItemEntity() {}
 
    public ItemEntity(String itemName, String description, String status, String date, CategoryEntity category, LocationEntity location) {
        this.itemName = itemName;
        this.description = description;
        this.status = status;
        this.date = date;
        //this.admin = admin;
        this.category = category;
        this.location = location;
    }
 
    public int getItemId() {
        return itemId;
    }
 
    public String getItemName() {
        return itemName;
    }
 
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	//public AdminEntity getAdmin() {
	//	return admin;
	//}

	//public void setAdmin(AdminEntity admin) {
	//	this.admin = admin;
	//}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public LocationEntity getLocation() {
		return location;
	}

	public void setLocation(LocationEntity location) {
		this.location = location;
	}
 
   
 
    
}
