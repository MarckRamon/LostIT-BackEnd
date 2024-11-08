package com.g4appdev.LostIT.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import com.fasterxml.jackson.annotation.JsonManagedReference;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*; 

@Entity
@Table(name = "location")
public class LocationEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LocationID")
    private int locationId;
 
    @Column(name = "LocationBuilding", nullable = false)
    private String locationBuilding;
 
    @Column(name = "LocationFloor")
    private String locationFloor;
    
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    //@JsonManagedReference
    @JsonIgnoreProperties("location")
    private List<ItemEntity> items;
 
    public LocationEntity() {}
 
    public LocationEntity(String locationBuilding, String locationFloor) {
        this.locationBuilding = locationBuilding;
        this.locationFloor = locationFloor;
    }
    
    public int getLocationId() {
		return locationId;
	}
 
	public String getLocationBuilding() {
		return locationBuilding;
	}
 
	public void setLocationBuilding(String locationBuilding) {
		this.locationBuilding = locationBuilding;
	}
 
	public String getLocationFloor() {
		return locationFloor;
	}
 
	public void setLocationFloor(String locationFloor) {
		this.locationFloor = locationFloor;
	}
 
	public List<ItemEntity> getItems() {
        return items;
    }
 
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}
