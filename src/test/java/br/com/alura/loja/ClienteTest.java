package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;

//cliente: teste que tenta acessar uma URI e verifica se o XML resultante � o que esperamos. Faz a requisi��o HTTP e confere que a representa��o que o servidor devolve � o que esperamos
public class ClienteTest {

	private HttpServer server;

	@Before
	public void sobeServidor() {
		this.server = Servidor.inicializaServidor();
	}

	@After
	public void mataServidor() {
		server.stop();
	}

	@Test
	public void testaQueAConexaoComOServidorFunciona() {

		// cliente http para acessar o servidor (javax.ws)
		Client client = ClientBuilder.newClient();

		// usar URI base,a do servidor, para fazer v�rias requisi��es
		WebTarget target = client.target("http://localhost:8080");

		// queremos fazer uma requisi��o para um path especifico epegar dados do
		// servidor (get) e converta o corpo da resposta em uma String
		String conteudo = target.path("/carrinhos/1").request().get(String.class);

		// certeza que o conte�do contem a 'Rua Vergueiro 3185', que ela contem o peda�o
		// do XML que nos interessa.
		Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));

	}

	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/carrinhos/1").request().get(String.class);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);

		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());

	}

}
