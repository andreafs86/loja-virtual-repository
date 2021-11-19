package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.alura.jdbc.factory.ConnectionFactory;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory cf = new ConnectionFactory();
		Connection con = cf.recuperarConexao();

		System.out.println("Fechando conexão!!");
		
		con.close();
	}

}
