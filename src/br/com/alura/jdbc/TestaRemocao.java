package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.jdbc.factory.ConnectionFactory;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory cf = new ConnectionFactory();
		try(Connection con = cf.recuperarConexao()){
			Statement st = con.createStatement();
			st.execute("delete from PRODUTO where id > 2");
			
			Integer linhasModificadas = st.getUpdateCount();
			
			System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);
		}

	}

}
