package com.rs.dojo.model.operadoras;

import java.math.BigDecimal;

public class Operadora {
	
	private Bandeira bandeira;
	private Boolean anuidadeVisa;
	private Boolean liberacaoTaxaMaster;
	/**
	 * 1 - Alimentação
	 * 2 - Refeição
	 */
	private Integer tipoCartaoSodexo;
	private BigDecimal taxaPagamento;
	
	public Bandeira getBandeira() {
		return bandeira;
	}
	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}
	public Boolean getAnuidadeVisa() {
		return anuidadeVisa;
	}
	public void setAnuidadeVisa(Boolean anuidadeVisa) {
		this.anuidadeVisa = anuidadeVisa;
	}
	public Boolean getLiberacaoTaxaMaster() {
		return liberacaoTaxaMaster;
	}
	public void setLiberacaoTaxaMaster(Boolean liberacaoTaxaMaster) {
		this.liberacaoTaxaMaster = liberacaoTaxaMaster;
	}
	public Integer getTipoCartaoSodexo() {
		return tipoCartaoSodexo;
	}
	public void setTipoCartaoSodexo(Integer tipoCartaoSodexo) {
		this.tipoCartaoSodexo = tipoCartaoSodexo;
	}
	public void setTaxaPagamento(BigDecimal taxaPagamento) {
		this.taxaPagamento = taxaPagamento;
	}
	public BigDecimal getTaxaPagamento() {
		return taxaPagamento;
	}
}
