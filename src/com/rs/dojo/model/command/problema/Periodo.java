package com.rs.dojo.model.command.problema;

import java.util.Date;

public class Periodo {

	private Date inicio;
	private Date fim;
	
	public Periodo(Date inicio, Date fim) {
		super();
		this.inicio = inicio;
		this.fim = fim;
	}
	
	public boolean estaDentroDoPeriodo(Date data){
		return data.equals(inicio) || data.equals(fim) || (data.after(inicio) && data.before(fim));
	}
	
}
