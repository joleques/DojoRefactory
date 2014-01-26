package com.rs.dojo.model.strategy.ideal;

import java.math.BigDecimal;

public class Operadora {

	private BigDecimal taxaPagamento = new BigDecimal(0);
	

	public void setTaxaPagamento(BigDecimal taxaPagamento) {
		this.taxaPagamento = taxaPagamento;
	}
	public BigDecimal getTaxaPagamento() {
		return taxaPagamento;
	}
}
