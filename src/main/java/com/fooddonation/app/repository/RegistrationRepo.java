package com.fooddonation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fooddonation.app.entity.RegistrationEntity;

@Repository
public interface RegistrationRepo extends JpaRepository<RegistrationEntity, String>
{

}
