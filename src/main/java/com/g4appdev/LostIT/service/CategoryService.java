package com.g4appdev.LostIT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4appdev.LostIT.entity.CategoryEntity;
import com.g4appdev.LostIT.repository.CategoryRepo;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        return categoryRepo.save(categoryEntity);
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryRepo.findAll();
    }

    public CategoryEntity updateCategoryDetails(int id, CategoryEntity newCategoryDetails) {
        CategoryEntity categoryEntity;
        try {
            categoryEntity = categoryRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Category not found"));

            categoryEntity.setCategoryName(newCategoryDetails.getCategoryName());

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Category with ID " + id + " not found!");
        }
        return categoryRepo.save(categoryEntity);
    }

    public String deleteCategory(int id) {
        if (categoryRepo.existsById(id)) {
            categoryRepo.deleteById(id);
            return "Category record successfully deleted!";
        } else {
            return "Category with ID " + id + " NOT FOUND!";
        }
    }
}

