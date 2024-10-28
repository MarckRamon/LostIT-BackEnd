package com.g4appdev.LostIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4appdev.LostIT.entity.LocationEntity;

public interface LocationRepo extends JpaRepository<LocationEntity,Integer>{

}
