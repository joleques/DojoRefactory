package com.rs.dojo.model.command.ideal;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class Vestibular {

	private Periodo periodo;
	
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
	public Periodo getPeriodo() {
		return periodo;
	}
	
	public void verificarPeriodo() throws VestibularException {
		if(!estaDentroDoPeriodo(dataAtual())){
			throw new VestibularException("fora do periodo");
		}
	}

	public Date dataAtual() {
		return Calendar.getInstance().getTime();
	}
	
	public boolean estaDentroDoPeriodo(Date dataAtual) {
		return periodo.estaDentroDoPeriodo(dataAtual);
	}
	
	public abstract void verificarProva() throws VestibularException;

	protected abstract List<VerificacaoCommand> verificacoes();
	
}
