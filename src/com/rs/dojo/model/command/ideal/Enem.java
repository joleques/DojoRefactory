package com.rs.dojo.model.command.ideal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Enem extends Vestibular{
	
	private List<Nota> notas = new ArrayList<Nota>();
	
	public Enem(List<Nota> notas) {
		super();
		this.notas = notas;
	}

	@Override
	protected List<VerificacaoCommand> verificacoes() {
		VerificacaoCommand periodo = new VerificacaoPeriodo();
		VerificacaoCommand data = new VerificarProva();
		return Arrays.asList(periodo,data);
	}

	@Override
	public void verificarProva() throws VestibularException {
		if (notas.isEmpty()) {
			throw new VestibularException("Sem prova para o vestibular");
		}
	}


}
