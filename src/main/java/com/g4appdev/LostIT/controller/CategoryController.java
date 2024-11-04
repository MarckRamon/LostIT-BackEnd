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

    @PostMapping("/createCategory")
    public CategoryEntity createCategory(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.createCategory(categoryEntity);
    }

    @GetMapping("/getAllCategories")
    public List<CategoryEntity> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/updateCategoryDetails/")
    public CategoryEntity updateCategoryDetails(@RequestParam int id, @RequestBody CategoryEntity newCategoryDetails) {
        return categoryService.updateCategoryDetails(id, newCategoryDetails);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }
}
