package com.rs.dojo.model.command.ideal;


public class VerificacaoPeriodo implements VerificacaoCommand {

	@Override
	public void verificar(Vestibular vestibular) throws VestibularException {
		vestibular.verificarPeriodo();
	}

}
