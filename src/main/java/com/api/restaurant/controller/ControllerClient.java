package com.api.restaurant.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.api.restaurant.entity.ClientEntity;
import com.api.restaurant.repository.ClientsRepository;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/")
public class ControllerClient {

	@Autowired
	private ClientsRepository clientsR;

	@GetMapping("helloClientes")
	public String hello() {
		return "Hello World";
	}

	@PostMapping("save_client")
	@ResponseStatus(HttpStatus.CREATED)
	public ClientEntity saveClient(@RequestBody ClientEntity client) {
		return clientsR.save(client);
	}

	@GetMapping("list_client")
	public Iterable<ClientEntity> getClients() {
		return clientsR.findAll();
	}

	@GetMapping("client_id/{id}")
	public ResponseEntity<ClientEntity> getClient(@PathVariable("id") Long id) {
		Optional<ClientEntity> res_client = clientsR.findById(id);
		if (res_client.isPresent() == true) {
			return new ResponseEntity<ClientEntity>(res_client.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("client_user/{email}")
	public ResponseEntity<ClientEntity> getClientEmail(@PathVariable("email") String email) {
		System.out.println(email + "=====================================");
		ClientEntity res_client = clientsR.findByEmail(email);
		if (res_client != null) {
			return new ResponseEntity<ClientEntity>(res_client, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("put_client")
	public ResponseEntity<ClientEntity> putClient(@RequestBody ClientEntity client) {
		Optional<ClientEntity> res_client = clientsR.findById(client.getId());
		if (res_client.isPresent() == true) {
			clientsR.save(client);
			return new ResponseEntity<ClientEntity>(res_client.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("delete_client/{email}")
	public ResponseEntity<ClientEntity> deleteClient(@PathVariable String email) {
		ClientEntity clientEntity = clientsR.findByEmail(email);
		if (clientEntity != null) {
			clientsR.deleteById(clientEntity.getId());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}