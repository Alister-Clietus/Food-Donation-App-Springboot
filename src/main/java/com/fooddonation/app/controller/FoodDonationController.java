package com.fooddonation.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fooddonation.app.dto.DonorDTO;
import com.fooddonation.app.dto.LoginDTO;
import com.fooddonation.app.dto.RegistrationDTO;
import com.fooddonation.app.dto.VolunteerDTO;
import com.fooddonation.app.service.FoodDonationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/fooddonation")
public class FoodDonationController 
{
	@Autowired
	FoodDonationService service;
	
	@PostMapping("/login")
	ResponseEntity<?> loginFunction(@Valid @RequestBody LoginDTO loginDTO) 
	{
	    System.out.println("Email and Password are ");
	    System.out.println(loginDTO.getEmail());
	    System.out.println(loginDTO.getPassword());
		return new ResponseEntity<>(service.loginFunction(loginDTO), HttpStatus.OK);
	}
	
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationDTO registrationDTO) {
        return new ResponseEntity<>(service.registerUser(registrationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/updateStatus/{email}")
    public ResponseEntity<?> updateUserStatus(@PathVariable String email) {
        return new ResponseEntity<>(service.updateUserStatus(email), HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        return new ResponseEntity<>(service.deleteUserByEmail(email), HttpStatus.OK);
    }
    
    @PostMapping("/donate")
    public ResponseEntity<?> donateFood(@Valid @RequestBody DonorDTO donorDTO) {
        return new ResponseEntity<>(service.donateFood(donorDTO), HttpStatus.CREATED);
    }

    @GetMapping("/food")
    public ResponseEntity<?> getAllFoodDonations() {
        return new ResponseEntity<>(service.getAllFoodDonations(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }
    
    @PutMapping("/updateDonationStatus/{foodId}")
    public ResponseEntity<?> updateDonationStatus(@PathVariable Long foodId) {
        return new ResponseEntity<>(service.updateDonationStatus(foodId), HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteDonation/{foodId}")
    public ResponseEntity<?> deleteDonation(@PathVariable Long foodId) {
        return new ResponseEntity<>(service.deleteDonation(foodId), HttpStatus.OK);
    }
    
    @PostMapping("/volunteerdetails")
    public ResponseEntity<?> volunteerDetils(@Valid @RequestBody VolunteerDTO volunteerDTO) 
    {
    	System.out.println("Entered the Vlunteerdetails");
        return new ResponseEntity<>(service.volunteerDetils(volunteerDTO), HttpStatus.CREATED);
    }
    
    @GetMapping("/volunteer/locations")
    public ResponseEntity<?> getLocations() {
        return new ResponseEntity<>(service.getLocations(), HttpStatus.OK);
    }

}
