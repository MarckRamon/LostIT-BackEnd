package com.g4appdev.LostIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4appdev.LostIT.entity.AdminEntity;

public interface AdminRepo extends JpaRepository<AdminEntity,Integer> {

}
