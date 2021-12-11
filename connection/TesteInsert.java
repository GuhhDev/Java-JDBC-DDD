package br.com.gustavo.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteInsert {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.recuperarConexao();

		Statement stm = connection.createStatement();
		stm.execute("INSERT INTO PESSOA (nome, descricao) VALUES ('gustavo','programador')",
				Statement.RETURN_GENERATED_KEYS);

		ResultSet rst = stm.getGeneratedKeys();
		while (rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O id criado foi: " + id);
		}
		rst.close();
		stm.close();
		connection.close();

	}
}
