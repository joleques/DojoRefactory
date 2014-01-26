package com.rs.dojo.model.strategy.ideal;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import com.rs.dojo.model.strategy.ideal.CadastroOperadora;
import com.rs.dojo.model.strategy.ideal.Operadora;
import com.rs.dojo.model.strategy.ideal.ServicoLegado;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.*;
import static com.rs.dojo.model.strategy.ideal.Bandeira.*;

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
	public void deveSerCobradoApenasTaxaAdesaoQuandoPossuirPlanoAnuidadeParaVisa() {
		OperadoraVisa visa = buildOperadoraVisa(true);
		cadastroOperadora.cadastrar(visa);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		Assert.assertEquals(new BigDecimal(5.5), operadoraCaptor.getValue().getTaxaPagamento());

	}

	@Test
	public void deveSerCobradoTaxaAdesaoMaisAdicionalEmpresaQuandoNaoPossuirPlanoAnuidadeParaVisa() {
		OperadoraVisa visa = buildOperadoraVisa(false);
		cadastroOperadora.cadastrar(visa);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		Assert.assertTrue(new BigDecimal(11).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);
	}

	@Test
	public void deveSerCobradoTaxaAdesaoQuandoNaoPossuirLiberacaoTaxaParaMaster() {
		OperadoraMaster visa = buildOperadoraMaster(false);
		cadastroOperadora.cadastrar(visa);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		Assert.assertTrue(new BigDecimal(8).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);

	}

	@Test
	public void naoDeveSerCobradoTaxaAdesaoQuandoPossuirLiberacaoTaxaParaMaster() {
		OperadoraMaster visa = buildOperadoraMaster(true);
		cadastroOperadora.cadastrar(visa);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		Assert.assertTrue(new BigDecimal(0).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);

	}

	@Test
	public void deveSerCobradoTaxaAdesaoTaxEmpresaTaxaAlimentacaoQuandoForSodexoAlimentacao() {
		OperadoraSodexo sodexo = buildOperadoraSodexo(1);
		cadastroOperadora.cadastrar(sodexo);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		Assert.assertTrue(new BigDecimal(9.5).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);
	}

	@Test
	public void deveSerCobradoTaxaAdesaoTaxEmpresaQuandoForSodexoRefeicao() {
		OperadoraSodexo sodexo = buildOperadoraSodexo(2);
		cadastroOperadora.cadastrar(sodexo);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		Assert.assertTrue(new BigDecimal(7).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);

	}

	@Test
	public void naoDeveCadastrarOperadoraSodexoQuandoForPassadoUmTipoDeValeInvalido() {
		OperadoraSodexo master = buildOperadoraSodexo(3);
		Assert.assertFalse(cadastroOperadora.cadastrar(master));
	}

	private OperadoraSodexo buildOperadoraSodexo(Integer tipoOperadoraSodexo) {
		OperadoraSodexo operadora = new OperadoraSodexo();
		operadora.setTipoCartaoSodexo(tipoOperadoraSodexo);
		return operadora;
	}
	

	private OperadoraVisa buildOperadoraVisa(Boolean planoAnuidade) {
		OperadoraVisa operadora = new OperadoraVisa();
		operadora.setAnuidadeVisa(planoAnuidade);
		return operadora;
	}
	

	private OperadoraMaster buildOperadoraMaster(Boolean liberacaoTaxa) {
		OperadoraMaster operadora = new OperadoraMaster();
		operadora.setLiberacaoTaxaMaster(liberacaoTaxa);
		return operadora;
	}

}
