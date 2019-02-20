package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Test;

public class ProjetoTest {

	@Test
	public void testaQueAConexaoComOServidorFunciona() {

		// cliente http para acessar o servidor (javax.ws)
		Client client = ClientBuilder.newClient();

		// usar URI base,a do servidor, para fazer várias requisições
		WebTarget target = client.target("http://localhost:8080");

		// queremos fazer uma requisição para um path especifico epegar dados do
		// servidor (get) e converta o corpo da resposta em uma String
		String conteudo = target.path("/projetos").request().get(String.class);

		System.out.println(conteudo);

		// certeza que o conteúdo contem a 'Rua Vergueiro 3185', que ela contem o pedaço
		// do XML que nos interessa.
		Assert.assertTrue(conteudo.contains("<nome>Minha loja"));

	}
}
