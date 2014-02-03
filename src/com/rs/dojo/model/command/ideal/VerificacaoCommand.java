package com.rs.dojo.model.command.ideal;

public interface VerificacaoCommand {
	
	void verificar(Vestibular vestibular) throws VestibularException;

}
