package com.rs.dojo.model.operadoras;

import static com.rs.dojo.model.operadoras.Bandeira.*;

import java.math.BigDecimal;

public class CadastroOperadora {
	
	private ServicoLegado servicoLegado;
	
	

	public CadastroOperadora(ServicoLegado servicoLegado) {
		this.servicoLegado = servicoLegado;
	}

	public void cadastrarVisa(Operadora visa) throws ExcecaoNegocio{
		if(!visa.getBandeira().equals(VISA))
			throw new ExcecaoNegocio("N�o eh operadora visa");
		BigDecimal taxaPagamento = new BigDecimal(0);
		if(visa.getAnuidadeVisa()){
			//para quem possuir plano anual n�o eh cobrado a taxa adicional da empresa
			taxaPagamento = new BigDecimal(5.5);
		}else{
			//para quem n�o possuir plano anual eh cobrado a taxa adicional da empresa
			taxaPagamento = new BigDecimal(5.5).add(new BigDecimal(5.5));
		}
		visa.setTaxaPagamento(taxaPagamento);
		servicoLegado.cadastrarOperadora(visa);
	}
	

	public void cadastrarMaster(Operadora master) throws ExcecaoNegocio{
		if(!master.getBandeira().equals(MASTER))
			throw new ExcecaoNegocio("N�o eh operadora master");
		BigDecimal taxaPagamento = new BigDecimal(0);
		if(!master.getLiberacaoTaxaMaster()){
			// se n�o possuir libera��o da taxa � cobrada a taxa com o adicional da empresa
			taxaPagamento = new BigDecimal(5.5).add(new BigDecimal(2.5));
		}
		master.setTaxaPagamento(taxaPagamento);
		servicoLegado.cadastrarOperadora(master);
	}
	


	public void cadastrarSodexo(Operadora sodexo) throws ExcecaoNegocio{
		if(!sodexo.getBandeira().equals(SODEXO))
			throw new ExcecaoNegocio("N�o eh operadora sodexo");
		BigDecimal taxaPagamento = new BigDecimal(0);
		if(sodexo.getTipoCartaoSodexo().equals(1)){
			//a taxa � igual ao valor da ades�o mais taxa da empresa
			taxaPagamento = new BigDecimal(5.5).add(new BigDecimal(1.5));

			// mais taxa da de alimenta��o
			taxaPagamento = taxaPagamento.add(new BigDecimal(2.5));
		}else if(sodexo.getTipoCartaoSodexo().equals(2)){
			//a taxa � igual ao valor da ades�o mais taxa da empresa
			taxaPagamento = new BigDecimal(5.5).add(new BigDecimal(1.5));
		}else{
			throw new ExcecaoNegocio("Tipo de vale invalido!");
		}
		sodexo.setTaxaPagamento(taxaPagamento);
		servicoLegado.cadastrarOperadora(sodexo);
	}

}
