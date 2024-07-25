package com.api.restaurant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="clients")
public class ClientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email
	@NotBlank
	@Size( max = 80)	
	@Column(unique = true)
    private String email;
	
	@NotNull
    private Long identity;
	
	@NotBlank
	@Size(max = 80)
    private String names;
	
	@NotBlank
	@Size(max = 80)
    private String surnames;
	
	@NotNull	
    private Long phone;
	
	@NotBlank
	@Size(max = 80)
    private String address;
	
	
	
}


