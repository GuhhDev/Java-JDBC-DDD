package br.com.alura.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private Integer id;
	private String nome;
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	public Categoria(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}

	public void adicionar(Pessoa pessoa) {
		pessoas.add(pessoa);
	}

	public List<Pessoa> getCategoria() {
		return pessoas;
	}

	@Override
	public String toString() {
		return this.nome;
	}
}
