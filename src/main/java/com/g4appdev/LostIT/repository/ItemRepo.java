package com.g4appdev.LostIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4appdev.LostIT.entity.ItemEntity;

public interface ItemRepo extends JpaRepository<ItemEntity,Integer>{

}
