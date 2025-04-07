package com.fooddonation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fooddonation.app.entity.VolunteerEntity;

@Repository
public interface VolunteerRepo extends JpaRepository<VolunteerEntity, Long>
{

}
