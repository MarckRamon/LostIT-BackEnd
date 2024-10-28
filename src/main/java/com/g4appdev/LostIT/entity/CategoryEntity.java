package com.g4appdev.LostIT.entity;

import java.util.List;

import jakarta.persistence.*;
 
@Entity
@Table(name = "category")
public class CategoryEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private int categoryId;
 
    @Column(name = "CategoryName", nullable = false)
    private String categoryName;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemEntity> items;
 
    public CategoryEntity() {}
 
    public CategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public int getCategoryId() {
		return categoryId;
	}
 
	public String getCategoryName() {
		return categoryName;
	}
 
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
 
	public List<ItemEntity> getItems() {
        return items;
    }
 
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}