package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos") // classe que representa um carrinho na internet, um recurso que vai devolver o
					// XML de um carrinho.
public class CarrinhoResource {

	@Path("{id}") // queremos receber o id (parametro) pela uri
	@GET // o que será produzido sera consumido pelo cliente através do método get
	@Produces(MediaType.APPLICATION_XML) // p/ o cliente saber q o que esta sendo produzido é um json
	public String busca(@PathParam("id") long id) {

		return new CarrinhoDAO().busca(id).toXML();// busca o carrinho 1 e retorna a representação em json

	}

	@POST
	@Consumes(MediaType.APPLICATION_XML) // nao retorna mais a string <status>sucesso</status> então não produz mais
											// nada e sim consome. Obrigado passar agora o content-type na requisição
	public Response adiciona(String conteudo) {

		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);

		URI uri = URI.create("/carrinhos/" + carrinho.getId()); // Location do recurso

		return Response.created(uri).build();// retorna o location e o satus code 201 (created)

		// teste com curl
		// curl -v -d "<br.com.alura.loja.modelo.Carrinho> <produtos>
		// <br.com.alura.loja.modelo.Produto> <preco>4000.0</preco> <id>6237</id>
		// <nome>Videogame 4</nome> <quantidade>1</quantidade>
		// </br.com.alura.loja.modelo.Produto> </produtos> <rua>Rua Vergueiro 3185, 8
		// andar</rua> <cidade>São Paulo</cidade>
		// <id>1</id></br.com.alura.loja.modelo.Carrinho>"
		// http://localhost:8080/carrinhos

		// passando agora o content-type adequado da nossa requisição
		// curl -v -H "Content-Type: application/xml" -d
		// "<br.com.alura.loja.modelo.Carrinho> <produtos>
		// <br.com.alura.loja.modelo.Produto> <preco>4000.0</preco> <id>6237</id>
		// <nome>Videogame 4</nome> <quantidade>1</quantidade>
		// </br.com.alura.loja.modelo.Produto> </produtos> <rua>Rua Vergueiro 3185, 8
		// andar</rua> <cidade>São Paulo</cidade>
		// <id>1</id></br.com.alura.loja.modelo.Carrinho>"
		// http://localhost:8080/carrinhos

	}
}
