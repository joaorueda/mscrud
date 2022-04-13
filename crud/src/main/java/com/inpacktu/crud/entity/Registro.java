package com.inpacktu.crud.entity;

import java.io.Serializable;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.inpacktu.crud.data.vo.RegistroVO;


@Document(collection="registro")
public class Registro implements Serializable{

	private static final long serialVersionUID = -3569372971737502909L;

	@Id
	private String documento;
	
	private String nome;
	
	private String endereco;
	
	private int idade;
	
	public static Registro create(RegistroVO regVO) {
		return new ModelMapper().map(regVO, Registro.class);
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Registro(String documento, String nome, String endereco, int idade) {
		super();
		this.documento = documento;
		this.nome = nome;
		this.endereco = endereco;
		this.idade = idade;
	}

	public Registro() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(documento, endereco, idade, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		return Objects.equals(documento, other.documento) && Objects.equals(endereco, other.endereco)
				&& idade == other.idade && Objects.equals(nome, other.nome);
	}
	
	
	
}
