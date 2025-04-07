package com.fooddonation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fooddonation.app.entity.DonorEntity;

@Repository
public interface DonarRepo extends JpaRepository<DonorEntity, Long>
{

}
