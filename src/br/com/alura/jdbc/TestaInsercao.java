package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.jdbc.factory.ConnectionFactory;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory cf = new ConnectionFactory();
		try(Connection con = cf.recuperarConexao()){
			try(Statement st = con.createStatement()){
				st.execute("insert into PRODUTO (nome, descricao) values ('Mouse', 'Mouse sem fio')", Statement.RETURN_GENERATED_KEYS);
				
				ResultSet rs = st.getGeneratedKeys();
				
				while(rs.next()) {
					Integer id = rs.getInt(1);
					System.out.println(id);
				}
			}
		}
	}
}
