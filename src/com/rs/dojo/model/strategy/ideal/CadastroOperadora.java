package com.rs.dojo.model.strategy.ideal;



public class CadastroOperadora {
	
	private ServicoLegado servicoLegado;
	
	public CadastroOperadora(ServicoLegado servicoLegado) {
		this.servicoLegado = servicoLegado;
	}

	public boolean cadastrar(CalculoTaxa operadora){
		try {
			operadora.calcularTaxa();
		} catch (ExcpetionNegocio e) {
			return false;
		}
		servicoLegado.cadastrarOperadora((Operadora)operadora);
		return true;
	}

}
