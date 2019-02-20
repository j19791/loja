package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {

	public static void main(String[] args) throws IOException {
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");// desejamos buscar no pacote
																					// br.com.alura.loja, tudo que tem
																					// aí dentro, quero que você busque
																					// como JAX-RS e utilize como
																					// serviço
		URI uri = URI.create("http://localhost:8080");// uri e a porta que desejo abrir meu servidor
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);// quero levantar um servidor do
																					// Grizzly

		System.out.println("Servidor rodando");

		System.in.read();// parar o servidor qdo o usuario clicar enter
		server.stop();
	}

}
