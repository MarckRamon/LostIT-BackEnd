package com.g4appdev.LostIT.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4appdev.LostIT.entity.ClaimEntity;
import com.g4appdev.LostIT.entity.ItemEntity;
import com.g4appdev.LostIT.repository.ClaimRepo;
import com.g4appdev.LostIT.repository.ItemRepo;

@Service
public class ClaimService {
	@Autowired
	private ClaimRepo claimRepo;
	
	@Autowired
	private ItemRepo itemRepo;
	
	public ClaimEntity createClaim(int itemId, ClaimEntity claim) {
	    Optional<ItemEntity> item = itemRepo.findById(itemId);

	    if (item.isPresent()) {
	        ItemEntity foundItem = item.get();

	        if (foundItem.getClaim() != null) {
	            return null;
	        }

	        if (claim.getStudEmail() == null || claim.getStudEmail().isEmpty()) {
	            throw new IllegalArgumentException("Student email must not be null.");
	        }

	        ClaimEntity savedClaim = claimRepo.save(claim);

	        foundItem.setClaim(savedClaim);
	        foundItem.setStatus("Claimed");
	        itemRepo.save(foundItem);

	        return savedClaim;
	    }
	    return null;
	}

	public List<ClaimEntity> getAllClaims() {
	        return claimRepo.findAll();
	}
	
	public Optional<ClaimEntity> getClaimById(int claimId){
		return claimRepo.findById(claimId);
	}
	
	public ClaimEntity updateClaim(int claimId, ClaimEntity claimDetails) {
		Optional<ClaimEntity> claim = claimRepo.findById(claimId);
		
		if(claim.isPresent()) {
			ClaimEntity existingClaim = claim.get();
			existingClaim.setStudEmail(claimDetails.getStudEmail());
	        existingClaim.setFirstName(claimDetails.getFirstName());
			existingClaim.setLastName(claimDetails.getLastName());
            existingClaim.setDateClaimed(claimDetails.getDateClaimed());
            return claimRepo.save(existingClaim);
		}
		return null;
	}
	
	public String deleteClaim(int claimId) {
        Optional<ClaimEntity> claim = claimRepo.findById(claimId);
        
        if (claim.isPresent()) {
            ClaimEntity existingClaim = claim.get();
            ItemEntity item = existingClaim.getItem();
            if (item != null) {
                item.setClaim(null);
                item.setStatus("Unclaimed");
                itemRepo.save(item);
            }
            claimRepo.deleteById(claimId);
            return "Succesfully deleted claim!";
        }
        return "Failed to delete claim";
    }
	
	
}
