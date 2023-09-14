package com.alura.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private DataSource dataSource;
	
	 public ConnectionFactory() {
		String url = "jdbc:mysql://localhost/hotel?useTimeZone=true&serverTimeZone=UTC";
		String user = "root";
		String password = "123456";
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setMaxPoolSize(10);
        
        this.dataSource = comboPooledDataSource;
    }

    public Connection recuperaConexion() {
        try {
        	System.out.println("Conexion aceptada");
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
