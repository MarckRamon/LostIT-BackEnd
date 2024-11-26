package com.g4appdev.LostIT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.g4appdev.LostIT.entity.AdminEntity;
import com.g4appdev.LostIT.repository.AdminRepo;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    public AdminEntity createAdmin(AdminEntity adminEntity) {
        return adminRepo.save(adminEntity);
    }
    
    public AdminEntity getAdminById(int id) {
        return adminRepo.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
    }
    
    public List<AdminEntity> getAllAdmins() {
        return adminRepo.findAll();
    }

    public AdminEntity updateAdminDetails(int id, AdminEntity newAdminDetails) {
        AdminEntity adminEntity;
        try {
            adminEntity = adminRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Admin not found"));

            adminEntity.setEmail(newAdminDetails.getEmail());
            adminEntity.setFirstName(newAdminDetails.getFirstName());
            adminEntity.setLastName(newAdminDetails.getLastName());
            adminEntity.setPhoneNumber(newAdminDetails.getPhoneNumber());
            
            // Keep existing profile picture if not overwritten
            if (newAdminDetails.getProfilePicture() != null) {
                adminEntity.setProfilePicture(newAdminDetails.getProfilePicture());
                adminEntity.setProfilePictureType(newAdminDetails.getProfilePictureType());
            }
            
            // Only update password if a new one is provided
            if (newAdminDetails.getPassword() != null && !newAdminDetails.getPassword().isEmpty()) {
                adminEntity.setPassword(newAdminDetails.getPassword());
            }

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Admin " + id + " not found!");
        }
        return adminRepo.save(adminEntity);
    }

    public AdminEntity updateProfilePicture(int id, MultipartFile file) throws IOException {
        AdminEntity adminEntity = adminRepo.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Admin not found"));

        adminEntity.setProfilePicture(file.getBytes());
        adminEntity.setProfilePictureType(file.getContentType());

        return adminRepo.save(adminEntity);
    }

    public String deleteAdmin(int id) {
        if (adminRepo.existsById(id)) {
            adminRepo.deleteById(id);
            return "Admin record successfully deleted!";
        } else {
            return "Admin with ID " + id + " NOT FOUND!";
        }
    }
}