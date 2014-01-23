package com.rs.dojo.model.helper;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;


public class HelperTest {

	private static final String NOME_PESSOA = "João sem Braço";
	private String cpfPessoa = PessoaFisica.class.getSimpleName() + "." + "cpf";
	private String nomePessoa = PessoaFisica.class.getSimpleName() + "." + "nome";
	private String cartaoPassword = Cartao.class.getSimpleName() + "." + "password";

	@Test
	public void deveRetornarLengthCpf() {
		final int expectedLength = Integer.parseInt("14");
		final Integer length = Helper.getLength(cpfPessoa);
		assertEquals(expectedLength, length.intValue());
	}


	@Test
	public void deveRetornarLabelDoCampoParaNomePessoa() {
		String label = Helper.getLabel(nomePessoa);
		assertEquals("Nome da Pessoa", label);
	}

	@Test
	public void deveRetornarValorDoAtributoNomeDaPessoa() {
		String value = Helper.getValue(nomePessoa, construirPessoaFisica());
		assertEquals(NOME_PESSOA, value);
	}

	@Test
	public void deveRetornarViewTagParaAtributoPassword() {
		String value = Helper.getViewTag(cartaoPassword);
		assertEquals("pessoa/_password.jsp", value);
	}

	@Test
	public void deveRetornarNuloQuandoAtributoPassadoNaoPossuiViewTag() {
		String value = Helper.getViewTag("pessoaFisica.name");
		assertNull(value);
	}

	private PessoaFisica construirPessoaFisica() {
		PessoaFisica entity = new PessoaFisica();
		entity.setNome(NOME_PESSOA);
		return entity;
	}

}
