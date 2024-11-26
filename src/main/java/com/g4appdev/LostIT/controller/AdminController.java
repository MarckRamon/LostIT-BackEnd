package com.g4appdev.LostIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.g4appdev.LostIT.entity.AdminEntity;
import com.g4appdev.LostIT.service.AdminService;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Base64;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "http://localhost:3000") // Add this for frontend compatibility
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/createAdmin")
    public AdminEntity createAdmin(@RequestBody AdminEntity adminEntity) {
        return adminService.createAdmin(adminEntity);
    }
    
    @GetMapping("/{id}")	
    public ResponseEntity<AdminEntity> getAdminById(@PathVariable int id) {
        AdminEntity admin = adminService.getAdminById(id);
        return ResponseEntity.ok(admin);
    }
    
    @GetMapping("/getAllAdmins")
    public List<AdminEntity> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PutMapping("/updateAdminDetails/{id}")
    public AdminEntity updateAdminDetails(@PathVariable int id, @RequestBody AdminEntity newAdminDetails) {
        return adminService.updateAdminDetails(id, newAdminDetails);
    }

    @PostMapping("/uploadProfilePicture/{id}")
    public ResponseEntity<String> uploadProfilePicture(
        @PathVariable int id, 
        @RequestParam("file") MultipartFile file
    ) {
        try {
            AdminEntity updatedAdmin = adminService.updateProfilePicture(id, file);
            return ResponseEntity.ok("Profile picture uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to upload profile picture");
        }
    }

    @GetMapping("/getProfilePicture/{id}")
    public ResponseEntity<String> getProfilePicture(@PathVariable int id) {
        AdminEntity admin = adminService.getAdminById(id);
        if (admin.getProfilePicture() != null) {
            String base64Image = Base64.getEncoder().encodeToString(admin.getProfilePicture());
            String imageType = admin.getProfilePictureType();
            return ResponseEntity.ok("data:" + imageType + ";base64," + base64Image);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/removeProfilePicture/{id}")
    public ResponseEntity<String> removeProfilePicture(@PathVariable int id) {
        try {
            adminService.removeProfilePicture(id);
            return ResponseEntity.ok("Profile picture removed successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable int id) {
        return adminService.deleteAdmin(id);
    }
}