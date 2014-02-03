package com.rs.dojo.model.command.problema;

import static com.rs.dojo.model.command.problema.TipoVestibular.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Vestibular {
	
	private Periodo periodo;
	private TipoVestibular tipo;
	private List<Prova> provas = new ArrayList<Prova>();
	
	
	
	public Vestibular(TipoVestibular tipo) {
		super();
		this.tipo = tipo;
	}

	public void abrirConcurso() throws VestibularException{
		if(tipo.equals(TRADICIONAL)){
			if(!periodo.estaDentroDoPeriodo(dataAtual())){
				throw new VestibularException("fora do periodo");
			}
			if(provas.isEmpty()){
				throw new VestibularException("Sem prova para o vestibular");
			}
			for (Prova prova : provas) {
				if (!periodo.estaDentroDoPeriodo(prova.getData())) {
					throw new VestibularException("Sem prova para o vestibular");
				}
			}
		}else if(tipo.equals(ENEM)){
			if(!periodo.estaDentroDoPeriodo(dataAtual())){
				throw new VestibularException("fora do periodo");
			}
		}
	}

	private Date dataAtual() {
		return Calendar.getInstance().getTime();
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}
	
}
