package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Test;

//cliente: teste que tenta acessar uma URI e verifica se o XML resultante é o que esperamos. Faz a requisição HTTP e confere que a representação que o servidor devolve é o que esperamos
public class ClienteTest {

	@Test
	public void testaQueAConexaoComOServidorFunciona() {

		// cliente http para acessar o servidor (javax.ws)
		Client client = ClientBuilder.newClient();

		// usar URI base,a do servidor, para fazer várias requisições
		WebTarget target = client.target("http://www.mocky.io");

		// queremos fazer uma requisição para um path especifico epegar dados do
		// servidor (get) e converta o corpo da resposta em uma String
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);

		// certeza que o conteúdo contem a 'Rua Vergueiro 3185', que ela contem o pedaço
		// do XML que nos interessa.
		Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));

	}

}
