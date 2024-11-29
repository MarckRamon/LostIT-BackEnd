package com.g4appdev.LostIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4appdev.LostIT.entity.ClaimEntity;

public interface ClaimRepo extends JpaRepository <ClaimEntity, Integer>{

}
