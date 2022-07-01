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
@Table(name = "tb_tipopartes")
public class TipoPartes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_tipopartes;
	
	private String desc_tipopartes;

}
