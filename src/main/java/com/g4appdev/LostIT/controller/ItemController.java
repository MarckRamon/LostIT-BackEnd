package com.g4appdev.LostIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.g4appdev.LostIT.entity.ItemEntity;
import com.g4appdev.LostIT.service.ItemService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;
    
    @PostMapping("/addItem")
    public ItemEntity createItem(@RequestBody ItemEntity itemEntity) {
        return itemService.createItem(itemEntity);
    }

    @GetMapping("/getAllItems")
    public List<ItemEntity> getAllItems() {
        return itemService.getAllItems();
    }

    @PutMapping("/updateItem/{id}")
    public ItemEntity updateItemDetails(@PathVariable int id, @RequestBody ItemEntity newItemDetails) {
        return itemService.updateItemDetails(id, newItemDetails);
    }

    @DeleteMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable int id) {
        return itemService.deleteItem(id);
    }
    
    @PostMapping("/addReportedItem")
    public ItemEntity createReportedItem(@RequestBody ItemEntity itemEntity) {
        itemEntity.setReportStatus("Reported");
        return itemService.createItem(itemEntity);
    }

    @PostMapping("/transferToInventory/{id}")
    public ItemEntity transferToInventory(@PathVariable int id) {
        ItemEntity item = itemService.getItemById(id);
        
        item.setReportStatus("In Inventory");
        item.setStatus("Unclaimed");
        
        return itemService.updateItemDetails(id, item);
    }
}