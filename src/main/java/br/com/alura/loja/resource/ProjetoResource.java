package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {

	@Path("{id}")
	@GET // o que será produzido sera consumido pelo cliente através do método get
	@Produces(MediaType.APPLICATION_JSON) // p/ o cliente saber q o que esta sendo produzido é um xml
	public String busca(@PathParam("id") long id) {

		return new ProjetoDAO().busca(id).toJson();// busca o projeto 1 e retorna a representação em XML

	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) {

		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		new ProjetoDAO().adiciona(projeto);

		URI uri = URI.create("/projetos/" + projeto.getId());
		return Response.created(uri).build();
	}

	@DELETE
	@Path("{id}")
	public Response removerProjeto(@PathParam("id") long id) {

		new ProjetoDAO().remove(id);

		return Response.ok().build();
	}
}
