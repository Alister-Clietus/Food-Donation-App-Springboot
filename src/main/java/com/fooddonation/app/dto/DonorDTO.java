package com.fooddonation.app.dto;

public class DonorDTO 
{
    private Long foodId;
    private String email; // linked to Registration
    private String location;
    private String amountOfFood;
    private String foodType; // Veg or Non-Veg
    private String foodItem;
    
	public Long getFoodId() 
	{
		return foodId;
	}
	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAmountOfFood() {
		return amountOfFood;
	}
	public void setAmountOfFood(String amountOfFood) {
		this.amountOfFood = amountOfFood;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public String getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}
    
    
}
