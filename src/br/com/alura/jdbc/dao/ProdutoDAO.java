package br.com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

public class ProdutoDAO {

	private Connection con;

	public ProdutoDAO(Connection con) {
		this.con = con;
	}

	public void salvar(Produto produto) throws SQLException {

		String sql = "insert into PRODUTO (nome, descricao) values (?, ?)";

		try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, produto.getNome());
			ps.setString(2, produto.getDescricao());

			ps.execute();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				while (rs.next()) {
					produto.setId(rs.getInt(1));
				}
			}
		}

	}

	public List<Produto> listar() throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();

		String sql = "select id, nome, descricao from PRODUTO";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.execute();

			try (ResultSet rs = ps.getResultSet()) {
				while (rs.next()) {
					Produto produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"));

					produtos.add(produto);
				}
			}
		}
		
		return produtos;
	}

	public List<Produto> buscar(Categoria ct) throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();

		System.out.println("Executando query de buscar produto por categoria");
		
		String sql = "select id, nome, descricao from PRODUTO where categoria_id = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, ct.getId());
			
			ps.execute();

			try (ResultSet rs = ps.getResultSet()) {
				while (rs.next()) {
					Produto produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"));

					produtos.add(produto);
				}
			}
		}
		
		return produtos;
	}
}
