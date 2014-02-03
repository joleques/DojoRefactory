package com.rs.dojo.model.command.ideal;

import java.util.List;

public class ChamadorCommand {

	private List<VerificacaoCommand> commands;

	public ChamadorCommand(List<VerificacaoCommand> commands) {
		super();
		this.commands = commands;
	}
	
	public void verificar(Vestibular vestibular) throws VestibularException{
		for (VerificacaoCommand command : commands) {
			command.verificar(vestibular);
		}
	}
}
