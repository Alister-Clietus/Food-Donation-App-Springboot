package com.fooddonation.app.dto;

public class VolunteerDTO 
{
    private String email;
    private Long foodId;
    private String location;
    
    
    
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public Long getFoodId() {
		return foodId;
	}
	public void setFoodId(Long foodId) 
	{
		this.foodId = foodId;
	}
    
    
}

