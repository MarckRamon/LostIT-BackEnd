package com.g4appdev.LostIT.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4appdev.LostIT.entity.ClaimEntity;
import com.g4appdev.LostIT.service.ClaimService;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {
	
	@Autowired
	private ClaimService claimService;
	
	
    @PostMapping("/createClaim/{itemId}")
    public ResponseEntity<?> createClaim(@PathVariable int itemId, @RequestBody ClaimEntity claim) {
        ClaimEntity newClaim = claimService.createClaim(itemId, claim);
        if (newClaim != null) {
            return ResponseEntity.ok(newClaim);
        }
        return ResponseEntity.badRequest().body("Failed to create claim");
    }

    // Get all claims
    @GetMapping("/getAllClaims")
    public ResponseEntity<List<ClaimEntity>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    // Get claim by ID
    @GetMapping("/getClaim/{id}")
    public ResponseEntity<?> getClaimById(@PathVariable int id) {
        Optional<ClaimEntity> claim = claimService.getClaimById(id);
        if (claim.isPresent()) {
            return ResponseEntity.ok(claim.get());
        }
        return ResponseEntity.notFound().build();
    }

    
    @PutMapping("/updateClaim/{id}")
    public ResponseEntity<?> updateClaim(@PathVariable int id, @RequestBody ClaimEntity claimDetails) {
        ClaimEntity updatedClaim = claimService.updateClaim(id, claimDetails);
        if (updatedClaim != null) {
            return ResponseEntity.ok(updatedClaim);
        }
        return ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("/deleteClaim/{id}")
    public ResponseEntity<String> deleteClaim(@PathVariable int id) {
        String result = claimService.deleteClaim(id);
        if ("Succesfully deleted claim!".equals(result)) {
            return ResponseEntity.ok(result); // Return success message
        }
        return ResponseEntity.status(404).body(result); // Return failure message
    }

}
