package com.api.restaurant.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.restaurant.entity.InsumosEntity;
import com.api.restaurant.repository.InsumosRepository;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/")
public class ControllerInsumos {
	
	@Autowired
	private InsumosRepository insumosRepository;
	
	@GetMapping("hello")
	public String hello() {
		return "Hello World";
	}
	
	
	@PostMapping("save_insumo")
	@ResponseStatus(HttpStatus.CREATED)
	public InsumosEntity saveInsumo(@RequestBody InsumosEntity insumosEntity) {
		return insumosRepository.save(insumosEntity);
	}
	
	
	@GetMapping("list_insumos")
	public Iterable<InsumosEntity> getInsumo() {
		return insumosRepository.findAll();		
	}
		
	@GetMapping("insumos_id/{id}")
	public ResponseEntity<InsumosEntity> getInsumo(@PathVariable("id") Long id) {
		Optional<InsumosEntity> res_insumo = insumosRepository.findById(id);
		if(res_insumo.isPresent() == true) {			
			return new ResponseEntity<InsumosEntity>(res_insumo.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	}
		
	@PutMapping("put_insumo")
	public ResponseEntity<InsumosEntity> putInsumo(@RequestBody InsumosEntity insumosEntity) {
		Optional<InsumosEntity> res_insumo = insumosRepository.findById(insumosEntity.getId());
		if(res_insumo.isPresent() == true) {
			insumosRepository.save(insumosEntity);
			return new ResponseEntity<InsumosEntity>(res_insumo.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	}	
	
	@DeleteMapping("delete_insumo/{id}")
	public ResponseEntity<HttpStatus> deleteInsumo(@PathVariable Long id) {
		Optional<InsumosEntity> insumosEntity = insumosRepository.findById(id);		
			if(insumosEntity.isPresent() == true) {
				insumosRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} 
	}
	
}