package com.g4appdev.LostIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.g4appdev.LostIT.entity.ItemEntity;
import com.g4appdev.LostIT.repository.ItemRepo;
import com.g4appdev.LostIT.service.ItemService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;
    private ItemRepo itemrepo;
    
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
        itemEntity.setReported(true);
        return itemService.createItem(itemEntity);
    }

    @PostMapping("/transferToInventory/{id}")
    public ItemEntity transferToInventory(@PathVariable int id) {
        ItemEntity item = itemrepo.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Item not found"));
        
        item.setReported(false);
        item.setStatus("Turned In"); 
        
        return itemrepo.save(item);
    }
}

