package br.com.alura.loja.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // para utilizar a biblioteca padr�o jaxb de serializacao)
@XmlAccessorType(XmlAccessType.FIELD)
public class Produto {

	private double preco;
	private long id;
	private String nome;
	private int quantidade;

	// obrigat�rio construtor sem argumentos, sen�o o jaxb n�o funciona
	public Produto() {

	}

	public Produto(long id, String nome, double preco, int quantidade) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double getPrecoTotal() {
		return quantidade * preco;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
