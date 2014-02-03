package com.rs.dojo.model.command.ideal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rs.dojo.model.command.ideal.VestibularException;

public class Tradicional extends Vestibular{

	private List<Prova> provas = new ArrayList<Prova>();
	
	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}
	public List<Prova> getProva() {
		return provas;
	}

	@Override
	public void verificarProva() throws VestibularException {
		if(!temProva()){
			throw new VestibularException("Sem prova para o vestibular");
		}
		for (Prova prova : getProva()) {
			if (!estaDentroDoPeriodo(prova.getData())) {
				throw new VestibularException("Sem prova para o vestibular");
			}
		}
		
	}

	@Override
	protected List<VerificacaoCommand> verificacoes() {
		VerificacaoCommand periodo = new VerificacaoPeriodo();
		VerificacaoCommand data = new VerificarProva();
		return Arrays.asList(periodo,data);
	}


	public boolean temProva() {
		return !provas.isEmpty();
	}
}
