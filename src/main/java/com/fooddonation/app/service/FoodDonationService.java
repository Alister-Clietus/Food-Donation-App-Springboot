package com.fooddonation.app.service;


import com.fooddonation.app.dto.DonorDTO;
import com.fooddonation.app.dto.LoginDTO;
import com.fooddonation.app.dto.RegistrationDTO;
import com.fooddonation.app.dto.ServiceResponse;


public interface FoodDonationService 
{
	public ServiceResponse loginFunction(LoginDTO logindto);
	
	public ServiceResponse registerUser(RegistrationDTO registrationDTO);

	public ServiceResponse donateFood(DonorDTO donorDTO);

	public ServiceResponse getAllFoodDonations();

	public ServiceResponse getAllUsers();
	
	public ServiceResponse updateUserStatus(String email);
	
	public ServiceResponse deleteUserByEmail(String email);
	
	public ServiceResponse updateDonationStatus(Long foodId);
	
	public ServiceResponse deleteDonation(Long foodId);

}
