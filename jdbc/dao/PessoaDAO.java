package br.com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Pessoa;

public class PessoaDAO {

	private Connection connection;

	public PessoaDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Pessoa pessoa) {
		try {
			String sql = "INSERT INTO PESSOA (NOME, DESCRICAO) VALUES (?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, pessoa.getNome());
				pstm.setString(2, pessoa.getDescricao());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						pessoa.setId(rst.getInt(1));
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void salvarComCategoria(Pessoa pessoa) {
		try {
			String sql = "INSERT INTO PESSOA (NOME, DESCRICAO, CATEGORIA_ID) VALUES (?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, pessoa.getNome());
				pstm.setString(2, pessoa.getDescricao());
				pstm.setInt(3, pessoa.getCategoriaId());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						pessoa.setId(rst.getInt(1));
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Pessoa> listar() {
			List<Pessoa> pessoas = new ArrayList<Pessoa>();
			
			try {
			String sql = "SELECT ID, NOME, DESCRICAO FROM PESSOA";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				trasformarResultSetEmProduto(pessoas, pstm);
			}
			return pessoas;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Pessoa> buscar(Categoria ct) {
			List<Pessoa> pessoas = new ArrayList<Pessoa>();
			
			try {
			String sql = "SELECT ID, NOME, DESCRICAO FROM PESSOA WHERE CATEGORIA_ID = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, ct.getId());
				pstm.execute();

				trasformarResultSetEmProduto(pessoas, pstm);
			}
			return pessoas;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(Integer id) {
		try {
			try (PreparedStatement stm = connection.prepareStatement("DELETE FROM PESSOA WHERE ID = ?")) {
				stm.setInt(1, id);
				stm.execute();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(String nome, String descricao, Integer id) {
		try {
			try (PreparedStatement stm = connection
					.prepareStatement("UPDATE PRODUTO P SET P.NOME = ?, P.DESCRICAO = ? WHERE ID = ?")) {
				stm.setString(1, nome);
				stm.setString(2, descricao);
				stm.setInt(3, id);
				stm.execute();

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void trasformarResultSetEmProduto(List<Pessoa> pessoas, PreparedStatement pstm) {
		try {
			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Pessoa pessoa = new Pessoa(rst.getInt(1), rst.getString(2), rst.getString(3));

					pessoas.add(pessoa);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
