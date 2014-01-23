package com.rs.dojo.model.helper;

public class PessoaFisica {

	@Length(max = 14)
	private String cpf;
	
	@Label(value ="Nome da Pessoa")
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}
	
	

}
