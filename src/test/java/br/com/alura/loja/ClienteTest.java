package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Test;

//cliente: teste que tenta acessar uma URI e verifica se o XML resultante � o que esperamos. Faz a requisi��o HTTP e confere que a representa��o que o servidor devolve � o que esperamos
public class ClienteTest {

	@Test
	public void testaQueAConexaoComOServidorFunciona() {

		// cliente http para acessar o servidor (javax.ws)
		Client client = ClientBuilder.newClient();

		// usar URI base,a do servidor, para fazer v�rias requisi��es
		WebTarget target = client.target("http://www.mocky.io");

		// queremos fazer uma requisi��o para um path especifico epegar dados do
		// servidor (get) e converta o corpo da resposta em uma String
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);

		// certeza que o conte�do contem a 'Rua Vergueiro 3185', que ela contem o peda�o
		// do XML que nos interessa.
		Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));

	}

}
