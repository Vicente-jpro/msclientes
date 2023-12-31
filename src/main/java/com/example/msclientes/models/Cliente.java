package com.example.msclientes.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer idCliente;

	@Column(name = "nome")
	private String nome;

	@Column(name = "bi")
	private String bi;

	@Column(name = "idade")
	private String idade;

}
