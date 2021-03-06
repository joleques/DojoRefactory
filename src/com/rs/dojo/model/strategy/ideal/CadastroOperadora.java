package com.rs.dojo.model.strategy.ideal;



public class CadastroOperadora {
	
	private OperadoraDAO servicoLegado;
	
	public CadastroOperadora(OperadoraDAO servicoLegado) {
		this.servicoLegado = servicoLegado;
	}

	public boolean cadastrar(Operadora operadora){
		try {
			operadora.calcularTaxa();
		} catch (ExcpetionNegocio e) {
			return false;
		}
		servicoLegado.cadastrarOperadora(operadora);
		return true;
	}

}
