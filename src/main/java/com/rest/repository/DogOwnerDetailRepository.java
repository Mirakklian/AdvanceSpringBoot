package com.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.entity.DogOwnerDetails;

@Repository
public interface DogOwnerDetailRepository  extends JpaRepository<DogOwnerDetails,Long> {
	
	Optional<DogOwnerDetails> findByNameAndRegistrationNo(String name, String regNo);

}
