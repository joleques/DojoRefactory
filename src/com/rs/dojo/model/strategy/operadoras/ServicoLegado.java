package com.rs.dojo.model.strategy.operadoras;

public class ServicoLegado {

	public void cadastrarOperadora(Operadora visa) {
		System.out.println("Operadora "+visa.getBandeira().toString() + " cadastrada com sucesso!");
	}

}
