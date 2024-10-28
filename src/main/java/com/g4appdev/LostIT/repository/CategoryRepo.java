package com.g4appdev.LostIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4appdev.LostIT.entity.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity,Integer>{

}
