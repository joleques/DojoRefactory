package com.rs.dojo.model.operadoras;


import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;


import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.*;
import static com.rs.dojo.model.operadoras.Bandeira.*;

public class CadastroOperadoraTest {
	
	private CadastroOperadora cadastroOperadora;
	@Mock
	private ServicoLegado servicoLegado;
	@Captor
	private ArgumentCaptor<Operadora> operadoraCaptor;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		cadastroOperadora = new CadastroOperadora(servicoLegado);
	}
	
	@Test
	public void naoDeveCadastrarOperadoraVisaQuandoForCastrarVisaMasBandeiraPassadaForDiferente(){
		Operadora visa = buildOperadora(MASTER, true, null, null);
		try {
			cadastroOperadora.cadastrarVisa(visa);
			Assert.fail("teste falhou");
		} catch (ExcecaoNegocio e) {
			Assert.assertEquals("Não eh operadora visa", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveCadastrarOperadoraMasterQuandoForCastrarMasterMasBandeiraPassadaForDiferente(){
		Operadora master = buildOperadora(VISA, true, null, null);
		try {
			cadastroOperadora.cadastrarMaster(master);
			Assert.fail("teste falhou");
		} catch (ExcecaoNegocio e) {
			Assert.assertEquals("Não eh operadora master", e.getMessage());
		}
	}
	

	@Test
	public void naoDeveCadastrarOperadoraSodexoQuandoForCastrarSodexoMasBandeiraPassadaForDiferente(){
		Operadora master = buildOperadora(MASTER, true, null, null);
		try {
			cadastroOperadora.cadastrarSodexo(master);
			Assert.fail("teste falhou");
		} catch (ExcecaoNegocio e) {
			Assert.assertEquals("Não eh operadora sodexo", e.getMessage());
		}
	}
	

	@Test
	public void deveSerCobradoApenasTaxaAdesaoQuandoPossuirPlanoAnuidadeParaVisa(){
		Operadora visa = buildOperadora(VISA, true, null, null);
		try {
			cadastroOperadora.cadastrarVisa(visa);
			verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());
			
			Assert.assertEquals(new BigDecimal(5.5), operadoraCaptor.getValue().getTaxaPagamento());
			
		} catch (ExcecaoNegocio e) {
			Assert.fail("teste falhou");
			Assert.assertEquals("Não eh operadora visa", e.getMessage());
		}
	}
	

	@Test
	public void deveSerCobradoTaxaAdesaoMaisAdicionalEmpresaQuandoNaoPossuirPlanoAnuidadeParaVisa(){
		Operadora visa = buildOperadora(VISA, false, null, null);
		try {
			cadastroOperadora.cadastrarVisa(visa);
			verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());
			
			Assert.assertTrue(new BigDecimal(11).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);
			
		} catch (ExcecaoNegocio e) {
			Assert.fail("teste falhou");
			Assert.assertEquals("Não eh operadora visa", e.getMessage());
		}
	}
	

	@Test
	public void deveSerCobradoTaxaAdesaoQuandoNaoPossuirLiberacaoTaxaParaMaster(){
		Operadora visa = buildOperadora(MASTER, true, false, null);
		try {
			cadastroOperadora.cadastrarMaster(visa);
			verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

			Assert.assertTrue(new BigDecimal(8).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);
			
		} catch (ExcecaoNegocio e) {
			Assert.fail("teste falhou");
			Assert.assertEquals("Não eh operadora visa", e.getMessage());
		}
	}
	

	@Test
	public void naoDeveSerCobradoTaxaAdesaoQuandoPossuirLiberacaoTaxaParaMaster(){
		Operadora visa = buildOperadora(MASTER, true, true, null);
		try {
			cadastroOperadora.cadastrarMaster(visa);
			verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

			Assert.assertTrue(new BigDecimal(0).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);
			
		} catch (ExcecaoNegocio e) {
			Assert.fail("teste falhou");
			Assert.assertEquals("Não eh operadora visa", e.getMessage());
		}
	}
	
	@Test
	public void deveSerCobradoTaxaAdesaoTaxEmpresaTaxaAlimentacaoQuandoForSodexoAlimentacao(){
		Operadora sodexo = buildOperadora(SODEXO, true, true, 1);
		try {
			cadastroOperadora.cadastrarSodexo(sodexo);
			verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

			Assert.assertTrue(new BigDecimal(9.5).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);
			
		} catch (ExcecaoNegocio e) {
			Assert.fail("teste falhou");
			Assert.assertEquals("Não eh operadora visa", e.getMessage());
		}
	}
	
	@Test
	public void deveSerCobradoTaxaAdesaoTaxEmpresaQuandoForSodexoRefeicao(){
		Operadora sodexo = buildOperadora(SODEXO, true, true, 2);
		try {
			cadastroOperadora.cadastrarSodexo(sodexo);
			verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

			Assert.assertTrue(new BigDecimal(7).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);
			
		} catch (ExcecaoNegocio e) {
			Assert.fail("teste falhou");
			Assert.assertEquals("Não eh operadora visa", e.getMessage());
		}
	}
	

	@Test
	public void naoDeveCadastrarOperadoraSodexoQuandoForPassadoUmTipoDeValeInvalido(){
		Operadora master = buildOperadora(SODEXO, true, null, 3);
		try {
			cadastroOperadora.cadastrarSodexo(master);
			Assert.fail("teste falhou");
		} catch (ExcecaoNegocio e) {
			Assert.assertEquals("Tipo de vale invalido!", e.getMessage());
		}
	}
	

	private Operadora buildOperadora(Bandeira bandeira, Boolean planoAnuidade, Boolean liberacaoTaxa, Integer tipoOperadoraSodexo) {
		Operadora operadora = new Operadora();
		operadora.setBandeira(bandeira);
		operadora.setAnuidadeVisa(planoAnuidade);
		operadora.setLiberacaoTaxaMaster(liberacaoTaxa);
		operadora.setTipoCartaoSodexo(tipoOperadoraSodexo);
		return operadora;
	}

}
