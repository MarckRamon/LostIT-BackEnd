package com.g4appdev.LostIT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4appdev.LostIT.entity.CategoryEntity;
import com.g4appdev.LostIT.entity.ItemEntity;
import com.g4appdev.LostIT.entity.LocationEntity;
import com.g4appdev.LostIT.repository.CategoryRepo;
import com.g4appdev.LostIT.repository.ItemRepo;
import com.g4appdev.LostIT.repository.LocationRepo;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;
    
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private LocationRepo locationRepo;

    // New method to get item by ID
    public ItemEntity getItemById(int id) {
        return itemRepo.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Item not found with ID: " + id));
    }

    // Existing methods remain the same...
    public ItemEntity createItem(ItemEntity itemEntity) {
        // Existing implementation
        CategoryEntity category = itemEntity.getCategory();
        if (category != null && (category.getCategoryId() == 0 || !categoryRepo.existsById(category.getCategoryId()))) {
            category = categoryRepo.save(category);
        } else if (category != null) {
            category = categoryRepo.findById(category.getCategoryId()).orElseThrow(() -> new NoSuchElementException("Category not found"));
        }

        LocationEntity location = itemEntity.getLocation();
        if (location != null && (location.getLocationId() == 0 || !locationRepo.existsById(location.getLocationId()))) {
            location = locationRepo.save(location);
        } else if (location != null) {
            location = locationRepo.findById(location.getLocationId()).orElseThrow(() -> new NoSuchElementException("Location not found"));
        }

        itemEntity.setCategory(category);
        itemEntity.setLocation(location);

        return itemRepo.save(itemEntity);
    }

    public List<ItemEntity> getAllItems() {
        return itemRepo.findAll();
    }

    public ItemEntity updateItemDetails(int id, ItemEntity newItemDetails) {
        ItemEntity itemEntity = getItemById(id);

        itemEntity.setItemName(newItemDetails.getItemName());
        itemEntity.setDescription(newItemDetails.getDescription());
        itemEntity.setStatus(newItemDetails.getStatus());
        itemEntity.setDate(newItemDetails.getDate());
        itemEntity.setCategory(newItemDetails.getCategory());
        itemEntity.setLocation(newItemDetails.getLocation());
        itemEntity.setReportStatus(newItemDetails.getReportStatus());

        return itemRepo.save(itemEntity);
    }

    public String deleteItem(int id) {
        if (itemRepo.existsById(id)) {
            itemRepo.deleteById(id);
            return "Item record successfully deleted!";
        } else {
            return "Item with ID " + id + " NOT FOUND!";
        }
    }
}