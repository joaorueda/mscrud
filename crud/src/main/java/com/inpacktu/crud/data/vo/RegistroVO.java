package com.inpacktu.crud.data.vo;

import java.io.Serializable;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.inpacktu.crud.entity.Registro;

@JsonPropertyOrder({"nome","documento","endereco","idade"})
public class RegistroVO extends RepresentationModel<RegistroVO> implements Serializable {

	private static final long serialVersionUID = 235488840709985862L;

	
	@JsonProperty("documento")
	private String documento;

	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("endereco")
	private String endereco;
	
	@JsonProperty("idade")
	private int idade;
	
	public static RegistroVO create(Registro reg) {
		return new ModelMapper().map(reg, RegistroVO.class);
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

	public RegistroVO(String documento, String nome, String endereco, int idade) {
		super();
		this.documento = documento;
		this.nome = nome;
		this.endereco = endereco;
		this.idade = idade;
	}

	public RegistroVO() {
		super();
	}

	public RegistroVO(Iterable<Link> initialLinks) {
		super(initialLinks);
	}

	public RegistroVO(Link initialLink) {
		super(initialLink);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(documento, endereco, idade, nome);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroVO other = (RegistroVO) obj;
		return Objects.equals(documento, other.documento) && Objects.equals(endereco, other.endereco)
				&& idade == other.idade && Objects.equals(nome, other.nome);
	}
	
	
}
