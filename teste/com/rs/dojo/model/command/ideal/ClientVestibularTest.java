package com.rs.dojo.model.command.ideal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ClientVestibularTest {

	private ClienteVestibular clienteVestibular;
	
	@Before
	public void setUp() {
		clienteVestibular = new ClienteVestibular();
	}
	
	@Test
	public void deveSubirExceptionForaPeriodoQuandoDataAtualEstiverForaPeriodoVestibularTradicional() {
		Tradicional vestibular = new Tradicional();
		vestibular.setPeriodo(criarPeriodo(Calendar.APRIL));
		vestibular.setProvas(criarProvas(createData(15,Calendar.FEBRUARY, 2014)));
		try {
			clienteVestibular.abrirVestibularTradicional(vestibular);
			Assert.fail("falhou teste");
		} catch (VestibularException excecao) {
			Assert.assertEquals("fora do periodo", excecao.getMessage());
		}
	}

	@Test
	public void deveSubirExceptionForaPeriodoQuandoDataAtualEstiverForaPeriodoVestibularEnem() {
		Enem vestibular = new Enem(criarNotas());
		vestibular.setPeriodo(criarPeriodo(Calendar.APRIL));
		try {
			clienteVestibular.abrirVestibularEnem(vestibular);
			Assert.fail("falhou teste");
		} catch (VestibularException excecao) {
			Assert.assertEquals("fora do periodo", excecao.getMessage());
		}
	}

	@Test
	public void naoDeveSubirExceptionQuandoDataAtualEstiverDentroPeriodoVestibularTradicional() {
		Tradicional vestibular = new Tradicional();
		vestibular.setPeriodo(criarPeriodo(Calendar.FEBRUARY));
		vestibular.setProvas(criarProvas(createData(15,Calendar.FEBRUARY, 2014)));
		try {
			clienteVestibular.abrirVestibularTradicional(vestibular);
		} catch (VestibularException excecao) {
			Assert.fail("falhou o teste");
		}
	}
	


	@Test
	public void naoDeveSubirExceptionQuandoDataAtualEstiverDentroPeriodoVestibularEnem() {
		Enem vestibular = new Enem(criarNotas());
		vestibular.setPeriodo(criarPeriodo(Calendar.FEBRUARY));
		try {
			clienteVestibular.abrirVestibularEnem(vestibular);
		} catch (VestibularException excecao) {
			Assert.fail("falhou o teste");
		}
	}


	@Test
	public void deveSubirExceptionQuandoDataProvaEstiverForaPeriodoVestibularTradicional() {
		Tradicional vestibular = new Tradicional();
		vestibular.setPeriodo(criarPeriodo(Calendar.FEBRUARY));
		vestibular.setProvas(criarProvas(createData(15,Calendar.MARCH, 2014)));
		try {
			clienteVestibular.abrirVestibularTradicional(vestibular);
		} catch (VestibularException excecao) {
			Assert.assertEquals("Sem prova para o vestibular", excecao.getMessage());
		}
	}
	

	@Test
	public void deveSubirExceptionQuandoNaoPossuirNotaVestibularEnem() {
		Enem vestibular = new Enem(new ArrayList<Nota>());
		vestibular.setPeriodo(criarPeriodo(Calendar.FEBRUARY));
		try {
			clienteVestibular.abrirVestibularEnem(vestibular);
		} catch (VestibularException excecao) {
			Assert.assertEquals("Sem prova para o vestibular", excecao.getMessage());
		}
	}
	

	@Test
	public void deveSubirExceptionQuandoNaoPossuiraProva() {
		Tradicional vestibular = new Tradicional();
		vestibular.setPeriodo(criarPeriodo(Calendar.FEBRUARY));
		try {
			clienteVestibular.abrirVestibularTradicional(vestibular);
			Assert.fail("falhou teste");
		} catch (VestibularException excecao) {
			Assert.assertEquals("Sem prova para o vestibular", excecao.getMessage());
		}
	}


	private List<Prova> criarProvas(Date data) {
		return Arrays.asList(new Prova(data));
	}
	

	private List<Nota> criarNotas() {
		return Arrays.asList(new Nota(new BigDecimal(600)));
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
