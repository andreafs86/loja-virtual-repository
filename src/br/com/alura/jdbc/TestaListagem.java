package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.jdbc.factory.ConnectionFactory;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory cf = new ConnectionFactory();
		try(Connection con = cf.recuperarConexao()){
			try(Statement st = con.createStatement()){
				st.execute("select id, nome, descricao from PRODUTO");
				
				ResultSet rs = st.getResultSet();
				
				while(rs.next()) {
					Integer id = rs.getInt("id");
					System.out.println(id);
					String nome = rs.getString("nome");
					System.out.println(nome);
					String descricao = rs.getString("descricao");
					System.out.println(descricao);
				}
				
			}
		}
	}
}
