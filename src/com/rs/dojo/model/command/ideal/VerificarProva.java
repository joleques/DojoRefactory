package com.rs.dojo.model.command.ideal;


public class VerificarProva implements VerificacaoCommand {

	@Override
	public void verificar(Vestibular vestibular) throws VestibularException {
		vestibular.verificarProva();
	}

}
