package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alura.loja.dao.ProjetoDAO;

@Path("projetos")
public class ProjetoResource {

	@Path("{id}")
	@GET // o que será produzido sera consumido pelo cliente através do método get
	@Produces(MediaType.APPLICATION_JSON) // p/ o cliente saber q o que esta sendo produzido é um xml
	public String busca(@PathParam("id") long id) {

		return new ProjetoDAO().busca(id).toJson();// busca o projeto 1 e retorna a representação em XML

	}
}
