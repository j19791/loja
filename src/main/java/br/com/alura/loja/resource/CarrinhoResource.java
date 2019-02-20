package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alura.loja.dao.CarrinhoDAO;

@Path("carrinhos") // classe que representa um carrinho na internet, um recurso que vai devolver o
					// XML de um carrinho.
public class CarrinhoResource {

	@GET // o que ser� produzido sera consumido pelo cliente atrav�s do m�todo get
	@Produces(MediaType.APPLICATION_XML) // p/ o cliente saber q o que esta sendo produzido � um xml
	public String busca() {

		return new CarrinhoDAO().busca(1l).toXML();// busca o carrinho 1 e retorna a representa��o em XML

	}
}
