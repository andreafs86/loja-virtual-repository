package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.jdbc.factory.ConnectionFactory;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory cf = new ConnectionFactory();

		try (Connection con = cf.recuperarConexao()) {

			con.setAutoCommit(false);

			try (PreparedStatement ps = con.prepareStatement("insert into PRODUTO (nome, descricao) values (?, ?)",
					Statement.RETURN_GENERATED_KEYS)) {

				adicionarVariavel("Mouse'", "Mouse sem fio); delete from PRODUTO;", ps);
				adicionarVariavel("SmartTV", "45 polegadas", ps);
				adicionarVariavel("Radio", "Radio de bateria", ps);

				con.commit();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK EXECUTADO");
				con.rollback();
			}
		}
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement ps) throws Exception {
		ps.setString(1, nome);
		ps.setString(2, descricao);

		if ("Radio".equals(nome)) {
			throw new RuntimeException("Não foi possível adicionar produto");
		}

		ps.execute();

		try (ResultSet rs = ps.getGeneratedKeys()) {
			while (rs.next()) {
				Integer id = rs.getInt(1);
				System.out.println("O id criado foi: " + id);
			}
		}

	}

}
