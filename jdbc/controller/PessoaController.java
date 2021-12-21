package br.com.alura.jdbc.controller;

import java.sql.Connection;
import java.util.List;

import br.com.alura.jdbc.dao.PessoaDAO;
import br.com.alura.jdbc.factory.ConnectionFactory;
import br.com.alura.jdbc.modelo.Pessoa;

public class PessoaController {
	
	private PessoaDAO pessoaDAO;
	
	public PessoaController() {
		Connection con = new ConnectionFactory().recuperarConexao();
		this.pessoaDAO = new PessoaDAO(con);
	}

	public void deletar(Integer id) {
		this.pessoaDAO.deletar(id);
	}

	public void salvar(Pessoa pessoa) {
		this.pessoaDAO.salvar(pessoa);
	}

	public List<Pessoa> listar() {
		return this.pessoaDAO.listar();
	}

	public void alterar(String nome, String descricao, Integer id) {
		this.pessoaDAO.alterar(descricao, descricao, id);
	}
}
