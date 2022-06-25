package com.proyecto.entidad;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_programa")
@AllArgsConstructor
@NoArgsConstructor
public class Programa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_programa;
	private int id_categoria;
	private String nom_prog;
	private String desc_prog;
	private String precio;

	public Programa(int id_programa) {
		this.id_programa = id_programa;
	}
}
