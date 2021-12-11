package br.com.gustavo.connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaConsulta {
	
	public static void main(String[] args) throws SQLException {
		
	ConnectionFactory con = new ConnectionFactory();
	
	PreparedStatement stm = con.recuperarConexao().prepareStatement("SELECT id, nome, descricao FROM PESSOA");
	stm.execute();
	
	ResultSet rst = stm.getResultSet();
		
		while (rst.next()) {
			int id = rst.getInt("id");
			String nome = rst.getString("nome");
			String descricao = rst.getString("descricao");
			System.out.println(id);
			System.out.println(nome);
			System.out.println(descricao);
		}

	}
}
