package com.g4appdev.LostIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.g4appdev.LostIT.entity.ItemEntity;
import com.g4appdev.LostIT.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Endpoint to create a new item
    @PostMapping("/addItem")
    public ItemEntity createItem(@RequestBody ItemEntity itemEntity) {
        return itemService.createItem(itemEntity);
    }

    // Endpoint to get all items
    @GetMapping("/getAllItems")
    public List<ItemEntity> getAllItems() {
        return itemService.getAllItems();
    }

    // Endpoint to update item details
    @PutMapping("/updateItem/{id}")
    public ItemEntity updateItemDetails(@PathVariable int id, @RequestBody ItemEntity newItemDetails) {
        return itemService.updateItemDetails(id, newItemDetails);
    }

    // Endpoint to delete an item by ID
    @DeleteMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable int id) {
        return itemService.deleteItem(id);
    }
}

