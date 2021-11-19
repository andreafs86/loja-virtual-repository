package br.com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

public class CategoriaDAO {

	private Connection con;

	public CategoriaDAO(Connection con) {
		this.con = con;
	}
	
	public List<Categoria> listar() throws SQLException{
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		System.out.println("Executando listagem de categoria");
		
		String sql = "select id, nome from CATEGORIA";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.execute();
			
			try(ResultSet rs = ps.getResultSet()){
				while(rs.next()) {
					Categoria categoria = new Categoria(rs.getInt("id"), rs.getString("nome"));
					categorias.add(categoria);
				}
			}
		}
		
		return categorias;
	}

	public List<Categoria> listarComProdutos() throws SQLException {
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		System.out.println("Executando listagem de categoria");
		
		String sql = "select c.id, c.nome, p.id, p.nome, p.descricao from CATEGORIA c inner join PRODUTO p on c.id = p.categoria_id";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.execute();
			
			try(ResultSet rs = ps.getResultSet()){
				while(rs.next()) {
					Categoria categoria = null;
					
					for(Categoria categoriaRecuperada : categorias) {
						if(rs.getInt(1) == categoriaRecuperada.getId()) {
							categoria = categoriaRecuperada;
							break;
						}
					}
					
					if(categoria == null) {
						categoria = new Categoria(rs.getInt(1), rs.getString(2));
						categorias.add(categoria);
					}
					
					Produto produto = new Produto(rs.getInt(3), rs.getString(4), rs.getString(5));
					categoria.adicionaProduto(produto);
				}
			}
		}
		
		return categorias;
	}
}
