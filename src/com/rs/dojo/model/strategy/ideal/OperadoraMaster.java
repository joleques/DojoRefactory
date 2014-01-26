package com.rs.dojo.model.strategy.ideal;

import java.math.BigDecimal;

public class OperadoraMaster extends Operadora {

	private Boolean liberacaoTaxaMaster;
	

	public Boolean getLiberacaoTaxaMaster() {
		return liberacaoTaxaMaster;
	}
	public void setLiberacaoTaxaMaster(Boolean liberacaoTaxaMaster) {
		this.liberacaoTaxaMaster = liberacaoTaxaMaster;
	}
	@Override
	public void calcularTaxa() {
		if(!this.getLiberacaoTaxaMaster()){
			setTaxaPagamento(new BigDecimal(5.5).add(new BigDecimal(2.5)));
		}
	}
}
