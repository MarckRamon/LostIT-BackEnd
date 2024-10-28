package com.g4appdev.LostIT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4appdev.LostIT.entity.ItemEntity;
import com.g4appdev.LostIT.repository.ItemRepo;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    public ItemEntity createItem(ItemEntity itemEntity) {
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
            itemEntity.setAdmin(newItemDetails.getAdmin());
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
