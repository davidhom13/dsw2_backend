package com.proyecto.entidad;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_programa")
public class ProgramasPC {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_programa;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCatePrograma")
	private CategoriaPrograma idCatePrograma;
	
	private String nom_prog;
	private String desc_prog;
	private String precio;

}
