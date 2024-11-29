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

    public ItemEntity createItem(ItemEntity itemEntity) {
        // Check if category needs to be created or already exists
        CategoryEntity category = itemEntity.getCategory();
        if (category != null && (category.getCategoryId() == 0 || !categoryRepo.existsById(category.getCategoryId()))) {
            category = categoryRepo.save(category); // mo create new category if wala
        } else if (category != null) {
            category = categoryRepo.findById(category.getCategoryId()).orElseThrow(() -> new NoSuchElementException("Category not found"));
        }

        // Check if location needs to be created or already exists
        LocationEntity location = itemEntity.getLocation();
        if (location != null && (location.getLocationId() == 0 || !locationRepo.existsById(location.getLocationId()))) {
            location = locationRepo.save(location); // mo create new location if wala
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
        ItemEntity itemEntity;
        try {
            itemEntity = itemRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Item not found"));

            itemEntity.setItemName(newItemDetails.getItemName());
            itemEntity.setDescription(newItemDetails.getDescription());
            itemEntity.setStatus(newItemDetails.getStatus());
            itemEntity.setDate(newItemDetails.getDate());
            //itemEntity.setAdmin(newItemDetails.getAdmin());
            itemEntity.setCategory(newItemDetails.getCategory());
            itemEntity.setLocation(newItemDetails.getLocation());
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Item with ID " + id + " not found!");
        }
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
