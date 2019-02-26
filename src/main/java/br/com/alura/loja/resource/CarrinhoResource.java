package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	@Produces(MediaType.APPLICATION_XML)
	public String adiciona(String conteudo) {

		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);

		return "<status>sucesso</status>";
	}
}
