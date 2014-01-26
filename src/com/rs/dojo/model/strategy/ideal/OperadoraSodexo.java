package com.rs.dojo.model.strategy.ideal;

import java.math.BigDecimal;

public class OperadoraSodexo extends Operadora{

	/**
	 * 1 - Alimentação
	 * 2 - Refeição
	 */
	private Integer tipoCartaoSodexo;

	public Integer getTipoCartaoSodexo() {
		return tipoCartaoSodexo;
	}
	public void setTipoCartaoSodexo(Integer tipoCartaoSodexo) {
		this.tipoCartaoSodexo = tipoCartaoSodexo;
	}
	
	@Override
	public void calcularTaxa() throws ExcpetionNegocio {
		if(getTipoCartaoSodexo().equals(1)){
			//a taxa é igual ao valor da adesão mais taxa da empresa
			setTaxaPagamento(new BigDecimal(5.5).add(new BigDecimal(1.5)));

			// mais taxa da de alimentação
			setTaxaPagamento(getTaxaPagamento().add(new BigDecimal(2.5)));
		}else if(getTipoCartaoSodexo().equals(2)){
			//a taxa é igual ao valor da adesão mais taxa da empresa
			setTaxaPagamento(new BigDecimal(5.5).add(new BigDecimal(1.5)));
		}else{
			throw new ExcpetionNegocio("Tipo invalido.");
		}

	}

}
