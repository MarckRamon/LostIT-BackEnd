package com.g4appdev.LostIT.controller;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.g4appdev.LostIT.entity.CategoryEntity;
import com.g4appdev.LostIT.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Endpoint to create a new category
    @PostMapping("/createCategory")
    public CategoryEntity createCategory(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.createCategory(categoryEntity);
    }

    // Endpoint to get all categories
    @GetMapping("/getAllCategories")
    public List<CategoryEntity> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Endpoint to update category details
    @PutMapping("/updateCategoryDetails/")
    public CategoryEntity updateCategoryDetails(@RequestParam int id, @RequestBody CategoryEntity newCategoryDetails) {
        return categoryService.updateCategoryDetails(id, newCategoryDetails);
    }

    // Endpoint to delete a category by ID
    @DeleteMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }
}
