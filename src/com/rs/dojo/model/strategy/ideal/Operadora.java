package com.rs.dojo.model.strategy.ideal;

import java.math.BigDecimal;

public abstract class Operadora  {

	private BigDecimal taxaPagamento = new BigDecimal(0);
	

	public void setTaxaPagamento(BigDecimal taxaPagamento) {
		this.taxaPagamento = taxaPagamento;
	}
	public BigDecimal getTaxaPagamento() {
		return taxaPagamento;
	}
	
	public abstract void calcularTaxa() throws ExcpetionNegocio;
}
