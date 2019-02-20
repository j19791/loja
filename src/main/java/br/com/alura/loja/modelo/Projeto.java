package br.com.alura.loja.modelo;

import com.thoughtworks.xstream.XStream;

public class Projeto {

	String nome;
	long id;
	int anoDeInicio;

	public Projeto(long id, String nome, int anoDeInicio) {

		this.nome = nome;
		this.id = id;
		this.anoDeInicio = anoDeInicio;
	}

	public String getNome() {
		return nome;
	}

	public long getId() {
		return id;
	}

	public int getAnoDeInicio() {
		return anoDeInicio;
	}

	public Projeto() {
		super();
	}

	public void setId(long id) {
		this.id = id;
	}

	public String toXML() {

		return new XStream().toXML(this);
	}

}
