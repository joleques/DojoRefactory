package com.rs.dojo.model.command.problema;

import static org.junit.Assert.*;
import static com.rs.dojo.model.command.problema.TipoVestibular.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class VestibularTest {

	private Vestibular vestibular;
	
	
	@Test
	public void deveSubirExceptionForaPeriodoQuandoDataAtualEstiverForaPeriodoVestibularTradicional() {
		vestibular = new Vestibular(TRADICIONAL);
		vestibular.setPeriodo(criarPeriodo(Calendar.APRIL));
		vestibular.setProvas(criarProvas(createData(15,Calendar.FEBRUARY, 2014)));
		try {
			vestibular.abrirConcurso();
		} catch (VestibularException excecao) {
			Assert.assertEquals("fora do periodo", excecao.getMessage());
		}
	}

	@Test
	public void deveSubirExceptionForaPeriodoQuandoDataAtualEstiverForaPeriodoVestibularEnem() {
		vestibular = new Vestibular(ENEM);
		vestibular.setPeriodo(criarPeriodo(Calendar.APRIL));
		try {
			vestibular.abrirConcurso();
		} catch (VestibularException excecao) {
			Assert.assertEquals("fora do periodo", excecao.getMessage());
		}
	}
	

	@Test
	public void naoDeveSubirExceptionQuandoDataAtualEstiverDentroPeriodoVestibularTradicional() {
		vestibular = new Vestibular(TRADICIONAL);
		vestibular.setPeriodo(criarPeriodo(Calendar.FEBRUARY));
		vestibular.setProvas(criarProvas(createData(15,Calendar.FEBRUARY, 2014)));
		try {
			vestibular.abrirConcurso();
		} catch (VestibularException excecao) {
			Assert.fail("falhou o teste");
		}
	}
	


	@Test
	public void naoDeveSubirExceptionQuandoDataAtualEstiverDentroPeriodoVestibularEnem() {
		vestibular = new Vestibular(ENEM);
		vestibular.setPeriodo(criarPeriodo(Calendar.FEBRUARY));
		try {
			vestibular.abrirConcurso();
		} catch (VestibularException excecao) {
			Assert.fail("falhou o teste");
		}
	}


	@Test
	public void deveSubirExceptionQuandoDataProvaEstiverForaPeriodoVestibularTradicional() {
		vestibular = new Vestibular(TRADICIONAL);
		vestibular.setPeriodo(criarPeriodo(Calendar.FEBRUARY));
		vestibular.setProvas(criarProvas(createData(15,Calendar.MARCH, 2014)));
		try {
			vestibular.abrirConcurso();
		} catch (VestibularException excecao) {
			Assert.assertEquals("Sem prova para o vestibular", excecao.getMessage());
		}
	}
	

	@Test
	public void deveSubirExceptionQuandoNaoPossuiraProva() {
		vestibular = new Vestibular(TRADICIONAL);
		vestibular.setPeriodo(criarPeriodo(Calendar.FEBRUARY));
		try {
			vestibular.abrirConcurso();
		} catch (VestibularException excecao) {
			Assert.assertEquals("Sem prova para o vestibular", excecao.getMessage());
		}
	}


	private List<Prova> criarProvas(Date data) {
		return Arrays.asList(new Prova(data));
	}


	private Periodo criarPeriodo(int mes) {
		Date inicio = createData(01,mes, 2014);
		Date fim = createData(28,mes, 2014);
		return new Periodo(inicio , fim);
	}

	private Date createData(int dia,int mes, int ano) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, dia);
		calendar.set(Calendar.MONTH, mes); 
		calendar.set(Calendar.YEAR, ano); 
		return calendar.getTime();
	}

}
