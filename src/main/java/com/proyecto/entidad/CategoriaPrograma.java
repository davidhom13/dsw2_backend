package com.proyecto.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_cateprogram")
public class CategoriaPrograma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCatePrograma;
	private String desPrograma;

}
