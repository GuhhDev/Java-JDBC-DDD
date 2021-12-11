package br.com.gustavo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection recuperarConexao() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://localhost/bancoTeste?useTimezone=true&serverTimezone=UTC", "root", "Admin123");
	}
}
