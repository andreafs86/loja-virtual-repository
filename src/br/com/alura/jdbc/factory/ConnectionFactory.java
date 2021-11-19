package br.com.alura.jdbc.factory;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	public DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mariadb://127.0.0.1:3306/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("admin");
		comboPooledDataSource.setPassword("admin");
		
		comboPooledDataSource.setMaxPoolSize(15);
		
		this.dataSource = comboPooledDataSource;
	}

	public Connection recuperarConexao() throws SQLException {
//		return DriverManager.getConnection(
//				"jdbc:mariadb://127.0.0.1:3306/loja_virtual?useTimezone=true&serverTimezone=UTC", "admin", "admin");
		return this.dataSource.getConnection();
	}
}
