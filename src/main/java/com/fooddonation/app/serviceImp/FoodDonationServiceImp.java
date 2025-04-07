package com.fooddonation.app.serviceImp;


import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddonation.app.dto.DonorDTO;
import com.fooddonation.app.dto.LoginDTO;
import com.fooddonation.app.dto.RegistrationDTO;
import com.fooddonation.app.dto.ServiceResponse;
import com.fooddonation.app.dto.VolunteerDTO;
import com.fooddonation.app.entity.DonorEntity;
import com.fooddonation.app.entity.RegistrationEntity;
import com.fooddonation.app.entity.VolunteerEntity;
import com.fooddonation.app.repository.DonarRepo;
import com.fooddonation.app.repository.RegistrationRepo;
import com.fooddonation.app.repository.VolunteerRepo;
import com.fooddonation.app.service.FoodDonationService;
import com.fooddonation.app.util.Constants;

@Service
public class FoodDonationServiceImp implements FoodDonationService
{

	@Autowired
	DonarRepo donarrepo;
	
	@Autowired 
	RegistrationRepo registrationrep;
	
	@Autowired
	VolunteerRepo volunteerrepo;
	
	public ServiceResponse loginFunction(LoginDTO logindto) 
	{
	    try {
	        Optional<RegistrationEntity> userOptional = registrationrep.findById(logindto.getEmail());

	        if (!userOptional.isPresent()) 
	        {
	        	System.out.println("User not present");
	            return new ServiceResponse(
	                Constants.MESSAGE_STATUS.FAILED,
	                "User with this email does not exist.",
	                null
	            );
	        }

	        RegistrationEntity user = userOptional.get();

	        if (!user.getPassword().equals(logindto.getPassword())) 
	        {
	        	System.out.println("Password Wrong");
	            return new ServiceResponse(
	                Constants.MESSAGE_STATUS.FAILED,
	                "Incorrect password.",
	                null
	            );
	        }

//	        if (!user.getRole().equalsIgnoreCase(logindto.getRole())) 
//	        {
//	        	System.out.println("Role Missmatched");
//	            return new ServiceResponse(
//	                Constants.MESSAGE_STATUS.FAILED,
//	                "Role mismatch.",
//	                null
//	            );
//	        }

	        // Login successful
	        JSONObject response = new JSONObject();
	        response.put("email", user.getEmail());
	        response.put("name", user.getName());
	        response.put("role", user.getRole());
	        response.put("status", user.getStatus());
	        
	        System.out.println(response);
	        
	        return new ServiceResponse(
	            Constants.MESSAGE_STATUS.SUCCESS,
	            null,response
	        );

	    } catch (Exception e) {
	        return new ServiceResponse(
	            Constants.MESSAGE_STATUS.FAILED,
	            e.getMessage(),null
	            
	        );
	    }
	}

	public ServiceResponse registerUser(RegistrationDTO registrationDTO) 
	{
	    try {
	        // Check if user already exists
	        Optional<RegistrationEntity> existingUser = registrationrep.findById(registrationDTO.getEmail());
	        if (existingUser.isPresent()) {
	        	
	            return new ServiceResponse(
	                Constants.MESSAGE_STATUS.FAILED,
	                "User with this email already exists.",
	                null
	            );
	        }
	        // Map DTO to Entity
	        RegistrationEntity registration = new RegistrationEntity();
	        registration.setEmail(registrationDTO.getEmail());
	        registration.setPhoneNumber(registrationDTO.getPhoneNumber());
	        registration.setName(registrationDTO.getName());
	        registration.setGender(registrationDTO.getGender());
	        registration.setAddress(registrationDTO.getAddress());
	        registration.setRole(registrationDTO.getRole());
	        registration.setPassword(registrationDTO.getPassword());
	        registration.setStatus("PENDING");
	        // Save to DB
	        registrationrep.save(registration);

	        return new ServiceResponse(
	            Constants.MESSAGE_STATUS.SUCCESS,
	            "User registered successfully.",
	            null
	        );

	    } catch (Exception e) {
	    	
	        return new ServiceResponse(
	            Constants.MESSAGE_STATUS.FAILED,
	            e.getMessage(),null
	            
	        );
	    }
	}

	public ServiceResponse donateFood(DonorDTO donorDTO) 
	{
	    try {
	        // Map DTO to Entity
	        DonorEntity donor = new DonorEntity();
	        donor.setEmail(donorDTO.getEmail());
	        donor.setLocation(donorDTO.getLocation());
	        donor.setAmountOfFood(donorDTO.getAmountOfFood());
	        donor.setFoodType(donorDTO.getFoodType());
	        donor.setFoodItem(donorDTO.getFoodItem());
	        donor.setStatus("NOTBOOKED");
	        // Save to database
	        DonorEntity saved = donarrepo.save(donor);
	        return new ServiceResponse(
	            Constants.MESSAGE_STATUS.SUCCESS,
	            "Food donation submitted successfully.",
	            null
	        );

	    } catch (Exception e) {
	        return new ServiceResponse(
	            Constants.MESSAGE_STATUS.FAILED,
	            e.getMessage(),null
	        );
	    }
	}

	public ServiceResponse getAllFoodDonations() 
	{
	    try {
	        List<DonorEntity> foodList = donarrepo.findAll();

	        if (foodList.isEmpty()) {
	            return new ServiceResponse(
	                Constants.MESSAGE_STATUS.SUCCESS,
	                "No food donations found.",
	                null
	            );
	        }

	        JSONArray aaDataArray = new JSONArray();

	        for (DonorEntity entity : foodList) {
	            JSONObject obj = new JSONObject();
	            obj.put("foodId", entity.getFoodId());
	            obj.put("email", entity.getEmail());
	            obj.put("location", entity.getLocation());
	            obj.put("amountOfFood", entity.getAmountOfFood());
	            obj.put("foodType", entity.getFoodType());
	            obj.put("foodItem", entity.getFoodItem());
	            obj.put("status", entity.getStatus());

	            aaDataArray.add(obj);
	        }

	        JSONObject responseMap = new JSONObject();
	        responseMap.put("aaData", aaDataArray);

	        return new ServiceResponse(
	            Constants.MESSAGE_STATUS.SUCCESS,
	            null,
	            responseMap
	        );

	    } catch (Exception e) {
	        return new ServiceResponse(
	            Constants.MESSAGE_STATUS.FAILED,
	            e.getMessage(),null
	            
	        );
	    }
	}

	public ServiceResponse getAllUsers() 
	{
	    try {
	        List<RegistrationEntity> userList = registrationrep.findAll();

	        if (userList.isEmpty()) {
	            return new ServiceResponse(
	                Constants.MESSAGE_STATUS.SUCCESS,
	                "No users found.",
	                null
	            );
	        }

	        JSONArray aaDataArray = new JSONArray();

	        for (RegistrationEntity user : userList) {
	            JSONObject obj = new JSONObject();
	            obj.put("email", user.getEmail());
	            obj.put("phoneNumber", user.getPhoneNumber());
	            obj.put("name", user.getName());
	            obj.put("gender", user.getGender());
	            obj.put("address", user.getAddress());
	            obj.put("role", user.getRole());
	            obj.put("status", user.getStatus());

	            aaDataArray.add(obj);
	        }

	        JSONObject responseMap = new JSONObject();
	        responseMap.put("aaData", aaDataArray);
	        return new ServiceResponse(
	            Constants.MESSAGE_STATUS.SUCCESS,
	            null,
	            responseMap
	        );

	    } catch (Exception e) {
	        return new ServiceResponse(
	            Constants.MESSAGE_STATUS.FAILED,
	            e.getMessage(),null
	            
	        );
	    }
	}
	
	public ServiceResponse updateUserStatus(String email) {
	    try {
	        Optional<RegistrationEntity> userOpt = registrationrep.findById(email);

	        if (!userOpt.isPresent()) {
	            return new ServiceResponse(Constants.MESSAGE_STATUS.FAILED, "User not found.", null);
	        }

	        RegistrationEntity user = userOpt.get();
	        user.setStatus("VERIFY");
	        registrationrep.save(user);

	        return new ServiceResponse(Constants.MESSAGE_STATUS.SUCCESS, "Status updated successfully.", null);
	    } catch (Exception e) {
	        return new ServiceResponse(Constants.MESSAGE_STATUS.FAILED,e.getMessage(),null);
	    }
	}
	
	public ServiceResponse deleteUserByEmail(String email) {
	    try {
	        if (!registrationrep.existsById(email)) {
	            return new ServiceResponse(Constants.MESSAGE_STATUS.FAILED, "User not found.", null);
	        }

	        registrationrep.deleteById(email);
	        return new ServiceResponse(Constants.MESSAGE_STATUS.SUCCESS, "User deleted successfully.", null);
	    } catch (Exception e) {
	        return new ServiceResponse(Constants.MESSAGE_STATUS.FAILED,e.getMessage(),null);
	    }
	}
	
	
	public ServiceResponse updateDonationStatus(Long foodId) {
	    try {
	        Optional<DonorEntity> donationOpt = donarrepo.findById(foodId);

	        if (!donationOpt.isPresent()) {
	            return new ServiceResponse(Constants.MESSAGE_STATUS.FAILED, "Donation not found.", null);
	        }

	        DonorEntity donation = donationOpt.get();
	        donation.setStatus("BOOKED");
	        donarrepo.save(donation);

	        return new ServiceResponse(Constants.MESSAGE_STATUS.SUCCESS, "Donation status updated to Booked.", null);
	    } catch (Exception e) {
	        return new ServiceResponse(Constants.MESSAGE_STATUS.FAILED,e.getMessage(),null);
	    }
	}
	
	public ServiceResponse deleteDonation(Long foodId) {
	    try {
	        if (!donarrepo.existsById(foodId)) {
	            return new ServiceResponse(Constants.MESSAGE_STATUS.FAILED, "Donation not found.", null);
	        }

	        donarrepo.deleteById(foodId);
	        return new ServiceResponse(Constants.MESSAGE_STATUS.SUCCESS, "Donation deleted successfully.", null);
	    } catch (Exception e) {
	        return new ServiceResponse(Constants.MESSAGE_STATUS.FAILED, e.getMessage(),null);
	    }
	}

	public ServiceResponse volunteerDetils(VolunteerDTO volunteerDTO) {
	    try {
	        VolunteerEntity entity = new VolunteerEntity();
	        entity.setEmail(volunteerDTO.getEmail());
	        entity.setFoodId(volunteerDTO.getFoodId());
	        entity.setAddress(volunteerDTO.getLocation());

	        VolunteerEntity saved = volunteerrepo.save(entity);
	        System.out.println("Entered");
	        return new ServiceResponse(Constants.MESSAGE_STATUS.SUCCESS, "Volunteer details saved successfully", null);
	    } catch (Exception e) 
	    {
	    	System.out.println("Exception");
	        return new ServiceResponse(Constants.MESSAGE_STATUS.FAILED, "Failed to save volunteer details", null);
	    }
	}
	
	public ServiceResponse getLocations() {
	    try {
	        List<VolunteerEntity> locationList = volunteerrepo.findAll();

	        if (locationList.isEmpty()) {
	            return new ServiceResponse(
	                Constants.MESSAGE_STATUS.SUCCESS,
	                "No locations found.",
	                null
	            );
	        }

	        JSONArray aaDataArray = new JSONArray();

	        for (VolunteerEntity location : locationList) {
	            JSONObject obj = new JSONObject();
	            obj.put("id", location.getId());
	            obj.put("email", location.getEmail());
	            obj.put("foodId", location.getFoodId());
	            obj.put("address", location.getAddress());

	            aaDataArray.add(obj);
	        }

	        JSONObject responseMap = new JSONObject();
	        responseMap.put("aaData", aaDataArray);
	        return new ServiceResponse(
	            Constants.MESSAGE_STATUS.SUCCESS,
	            null,
	            responseMap
	        );

	    } catch (Exception e) {
	        return new ServiceResponse(
	            Constants.MESSAGE_STATUS.FAILED,
	            e.getMessage(),
	            null
	        );
	    }
	}







	
}
