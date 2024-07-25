package com.api.restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import com.api.restaurant.entity.ClientEntity;


public interface ClientsRepository extends CrudRepository <ClientEntity, Long> {	
	
	ClientEntity findByEmail(String email);
	
}
