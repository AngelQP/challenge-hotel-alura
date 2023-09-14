package com.alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.alura.model.Huesped;

public class HuespedDao {
	
	private final Connection con;
	
	public HuespedDao(Connection con) {
		this.con = con;
	}
	
	public boolean guardar(Huesped huesped) {
		
		boolean state = false;
		
		try(con) {
			
			PreparedStatement statement;
			String sql = "INSERT INTO tbl_huespedes "
					+ "(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			try(statement){
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setString(3, huesped.getFechaNacimiento().toString());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getIdReserva());
				
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();
				
				try(resultSet){
					while(resultSet.next()) {
						huesped.setId(resultSet.getInt(1));
					}
				}
				
			}
			
			JOptionPane.showMessageDialog(null, "Registro realizado con éxito.");
			state = true;
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error. Vuelva a intentarlo.");
			throw new RuntimeException(e);
		}
		
		return state;
	}

	public boolean update(Huesped huesped) {
		boolean state = false;
		
		try(con) {
			
			String sql = "UPDATE tbl_huespedes SET "
					+ "nombre = ?, "
					+ "apellido = ?, "
					+ "fechaNacimiento = ?, "
					+ "nacionalidad = ?, "
					+ "telefono = ? "
					+ "WHERE id = ?";
			final PreparedStatement statement = con.prepareStatement(sql);
			
			try(statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setString(3, huesped.getFechaNacimiento().toString());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getId());
				
				statement.execute();
				
				state = true;
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return state;
	}

	public List<Huesped> listar(String busqueda) {
		 List<Huesped> resultado = new ArrayList<>();

	        try {
	        	String sql = "SELECT * FROM tbl_huespedes "
	        			+ "WHERE apellido LIKE ?";
	            final PreparedStatement statement = con.prepareStatement(sql);
	    
	            try (statement) {
	            	statement.setString(1, "%" + busqueda + "%");
	                statement.execute();
	    
	                final ResultSet resultSet = statement.getResultSet();
	    
	                try (resultSet) {
	                    while (resultSet.next()) {
	                        resultado.add(new Huesped(
	                                resultSet.getInt("id"),
	                                resultSet.getString("nombre"),
	                                resultSet.getString("apellido"),
	                                resultSet.getDate("fechaNacimiento"),
	                                resultSet.getString("nacionalidad"),
	                                resultSet.getString("telefono"),
	                                resultSet.getInt("idReserva")));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

	        return resultado;
	}
	
	
	

}
