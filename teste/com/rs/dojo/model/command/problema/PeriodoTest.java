package com.rs.dojo.model.command.problema;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import junit.framework.Assert;

import org.junit.Test;

public class PeriodoTest {
	
	private Periodo periodo;
	

	@Test
	public void deveRetornarVerdadeiroQuandoDataPassadaEstiverDentroPeriodo() {

		Date inicio = createData(01, Calendar.JANUARY, 2014);
		Date fim = createData(31, Calendar.JANUARY, 2014);
		periodo = new Periodo(inicio , fim);
		
		Assert.assertTrue(periodo.estaDentroDoPeriodo(createData(15, Calendar.JANUARY, 2014)));

	}


	@Test
	public void deveRetornarVerdadeiroQuandoDataPassadaEstiverIgualDataInio() {

		Date inicio = createData(01, Calendar.JANUARY, 2014);
		Date fim = createData(31, Calendar.JANUARY, 2014);
		periodo = new Periodo(inicio , fim);
		
		Assert.assertTrue(periodo.estaDentroDoPeriodo(createData(01, Calendar.JANUARY, 2014)));

	}


	@Test
	public void deveRetornarVerdadeiroQuandoDataPassadaEstiverIgualDataFim() {

		Date inicio = createData(01, Calendar.JANUARY, 2014);
		Date fim = createData(31, Calendar.JANUARY, 2014);
		periodo = new Periodo(inicio , fim);
		
		Assert.assertTrue(periodo.estaDentroDoPeriodo(createData(31, Calendar.JANUARY, 2014)));

	}

	@Test
	public void deveRetornarFalsoQuandoDataPassadaEstiverMaiorQueDataFim() {

		Date inicio = createData(01, Calendar.JANUARY, 2014);
		Date fim = createData(31, Calendar.JANUARY, 2014);
		periodo = new Periodo(inicio , fim);
		
		Assert.assertFalse(periodo.estaDentroDoPeriodo(createData(15, Calendar.FEBRUARY, 2014)));

	}
	

	@Test
	public void deveRetornarFalsoQuandoDataPassadaEstiverMenorQueDataInicio() {

		Date inicio = createData(01, Calendar.JANUARY, 2014);
		Date fim = createData(31, Calendar.JANUARY, 2014);
		periodo = new Periodo(inicio , fim);
		
		Assert.assertFalse(periodo.estaDentroDoPeriodo(createData(15, Calendar.FEBRUARY, 2013)));

	}
	
	private Date createData(int dia,int mes, int ano) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, dia);
		calendar.set(Calendar.MONTH, mes); 
		calendar.set(Calendar.YEAR, ano); 
		return calendar.getTime();
	}

}
