package com.rs.dojo.model.strategy.ideal;

import java.math.BigDecimal;

public class OperadoraVisa extends Operadora implements CalculoTaxa{

	private Boolean anuidadeVisa;
	

	public Boolean getAnuidadeVisa() {
		return anuidadeVisa;
	}
	public void setAnuidadeVisa(Boolean anuidadeVisa) {
		this.anuidadeVisa = anuidadeVisa;
	}
	
	@Override
	public void calcularTaxa() {
		if(getAnuidadeVisa()){
			//para quem possuir plano anual não eh cobrado a taxa adicional da empresa
			setTaxaPagamento(new BigDecimal(5.5));
		}else{
			//para quem não possuir plano anual eh cobrado a taxa adicional da empresa
			setTaxaPagamento(new BigDecimal(5.5).add(new BigDecimal(5.5)));
		}
	}

}
