package com.api.restaurant.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.api.restaurant.entity.InsumosEntity;

@DataJpaTest
public class InsumosRepositoryTest {
	
	@Autowired
	private InsumosRepository insumosRepository;
	
	private InsumosEntity insumosEntity;
	
	@BeforeEach
	void setup() {
		insumosEntity = InsumosEntity.builder()
				.codigo(02)
				.nombre("Harina Maiz")
				.inventario(10)
				.tipo("Harinas")
				.unidadPresentacion("500g")
				.build();
		System.out.println("***************#########-PRUEBAS UNITARIAS-################***************************");
	}
	
	@DisplayName("Test guardar nuevo insumo")
	@Test
	void saveInsumo() {
		InsumosEntity in_res = insumosRepository.save(insumosEntity);
		assertEquals(02,in_res.getCodigo());
		assertEquals("Harina Maiz",in_res.getNombre());
		assertEquals(10,in_res.getInventario());
		assertEquals("Harinas",in_res.getTipo());
		assertEquals("500g",in_res.getUnidadPresentacion());
	}
	
	@DisplayName("Test actualizar nuevo insumo")
	@Test
	void updateInsumo() {	
		InsumosEntity in_res = insumosRepository.save(insumosEntity);
		in_res.setNombre("Harina Papa");
		InsumosEntity in_update = insumosRepository.save(in_res);
		assertEquals(02,in_res.getCodigo());
		assertEquals("Harina Papa",in_update.getNombre());
		assertEquals(10,in_res.getInventario());
		assertEquals("Harinas",in_res.getTipo());
		assertEquals("500g",in_res.getUnidadPresentacion());
	}
	
	@DisplayName("Test eliminar insumo")
	@Test
	void eliminarInsumo() {	
			InsumosEntity in_res = insumosRepository.save(insumosEntity);		
			insumosRepository.deleteById(in_res.getId());
			Optional<InsumosEntity> in_resB = insumosRepository.findById(in_res.getId());
			assertThat(in_res).isNotNull();
			assertThat(in_resB).isEmpty();
	}
}
