package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

//cliente: teste que tenta acessar uma URI e verifica se o XML resultante é o que esperamos. Faz a requisição HTTP e confere que a representação que o servidor devolve é o que esperamos
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

		// posso ver o que é que o meu cliente JAX-RS está enviando para o servidor e o
		// que é que o servidor está devolvendo configurando o meu cliente.
		ClientConfig config = new ClientConfig();

		// registrar uma API de log do Jersey (implmentação do JaX-RS
		config.register(new LoggingFilter());

		// cliente http para acessar o servidor (javax.ws) baseado na configuração acima
		Client client = ClientBuilder.newClient(config);

		// usar URI base,a do servidor, para fazer várias requisições
		WebTarget target = client.target("http://localhost:8080");

		// queremos fazer uma requisição para um path especifico epegar dados do
		// servidor (get) e converta o corpo da resposta em uma String
		Carrinho carrinho = target.path("/carrinhos/1").request().get(Carrinho.class);// utilizadno jaxb

		System.out.println(carrinho.getProdutos().get(0).getNome());

		Assert.assertEquals("Microfone", carrinho.getProdutos().get(0).getNome());// NOEM DO PRIMEIRO PRODUTO

	}

	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/carrinhos/1").request().get(String.class);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);

		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());

	}

	@Test
	public void testaAdicaoDeCarrinhoViaPost() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");

		// Precisamos criar um carrinho e transforma-lo em XML
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
		carrinho.setRua("Rua Vergueiro");
		carrinho.setCidade("Sao Paulo");
		String xml = carrinho.toXML();

		// precisamos representar o carrinho: o conteúdo e o media type. A Entity é
		// utilizada para representar o que será enviado.
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);

		Response response = target.path("/carrinhos").request().post(entity);

		String location = response.getHeaderString("Location");

		String conteudo = client.target(location).request().get(String.class);

		Assert.assertEquals(201, response.getStatus());
		Assert.assertTrue(conteudo.contains("Tablet"));

	}

}
