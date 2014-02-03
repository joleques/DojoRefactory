package com.rs.dojo.model.command.ideal;

import java.util.Arrays;
import java.util.List;

public class ClienteVestibular {
	
	protected ChamadorCommand chamador;

	public void abrirVestibularTradicional(Vestibular vestibular) throws VestibularException {
		verificar(vestibular,verificacoesTradicional());
		abrir();
	}

	public void abrirVestibularEnem(Vestibular vestibular) throws VestibularException {
		verificar(vestibular,verificacoesEnem());
		abrir();
	}

	private void verificar(Vestibular vestibular, List<VerificacaoCommand> commands) throws VestibularException {
		chamador = new ChamadorCommand(commands);
		chamador.verificar(vestibular);
	}

	private void abrir() {
		System.out.println("Vestibular Aberto");
	}

	private List<VerificacaoCommand> verificacoesEnem() {
		VerificacaoCommand periodo = new VerificacaoPeriodo();
		VerificacaoCommand data = new VerificarProva();
		return Arrays.asList(periodo,data);
	}

	private List<VerificacaoCommand> verificacoesTradicional() {
		VerificacaoCommand periodo = new VerificacaoPeriodo();
		VerificacaoCommand data = new VerificarProva();
		return Arrays.asList(periodo,data);
	}
}
