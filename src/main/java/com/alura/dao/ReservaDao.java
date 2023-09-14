package com.alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.model.Reserva;

public class ReservaDao {

	private final Connection con;
	
	public ReservaDao(Connection con) {
		this.con = con;
	}
	
	// El objeto reserva ya cuenta con un ID de reserva
	// generado al realizar el guardado
	public void guardar(Reserva reserva) {
		
		try(con) {
			PreparedStatement statement;
			statement = con.prepareStatement(
					"INSERT INTO tbl_reservas " 
					+ "(FECHAENTRADA, FECHASALIDA, VALOR, FORMAPAGO)"
					+ "VALUES (?, ?, ? ,?)", Statement.RETURN_GENERATED_KEYS);
			
			try(statement) {
				statement.setString(1, reserva.getFechaEntrada().toString());
				statement.setString(2, reserva.getFechaSalida().toString());
				statement.setInt(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();
				
				try(resultSet) {
					while(resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
					}
				}
				
			}
			
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
	}
	
	public int eliminar(Integer id) {
		
        try(con) {
        	String sql = "DELETE FROM tbl_reservas WHERE idReserva = ?";
            final PreparedStatement statement = con.prepareStatement(sql);

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);        
        }
        
	}

	public Reserva listar(Integer busqueda) {
		Reserva reserva = null;
		
		try(con) {
			
			String sql = "SELECT idReserva, fechaEntrada, fechaSalida, valor, formaPago "
					+ "FROM tbl_reservas WHERE idReserva = ?";
			final PreparedStatement statement = con.prepareStatement(sql);
			
			try(statement) {
				statement.setInt(1, busqueda);
				
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try(resultSet) {
					while(resultSet.next()) {
						reserva = new Reserva(
								resultSet.getInt("idReserva"),
								resultSet.getDate("fechaEntrada"),
								resultSet.getDate("fechaSalida"),
								resultSet.getInt("valor"),
								resultSet.getString("formaPago")
								);
					}
				}
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return reserva;
	}

	public boolean update(Reserva reserva) {
		
		boolean state = false;
		
		try(con) {
			
			String sql = "UPDATE tbl_reservas SET "
					+ "fechaEntrada = ?, "
					+ "fechaSalida = ?, "
					+ "valor = ?, "
					+ "formaPago = ? "
					+ "WHERE idReserva = ?";
			final PreparedStatement statement = con.prepareStatement(sql);
			
			try(statement) {
				statement.setString(1, reserva.getFechaEntrada().toString());
				statement.setString(2, reserva.getFechaSalida().toString());
				statement.setInt(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				statement.setInt(5, reserva.getId());
				
				statement.execute();
				
				state = true;
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return state;
	}

	public boolean delete(Integer id) {
		
		boolean state = false;
		try(con) {
			
			String sqlDeleteReserva = "DELETE FROM tbl_reservas WHERE idReserva = " + id;
			String sqlDelelteHuesped = "DELETE FROM tbl_huespedes WHERE idReserva = " + id;
			final Statement statement = con.createStatement();
			
			try(statement) {
				statement.addBatch(sqlDeleteReserva);
				statement.addBatch(sqlDelelteHuesped);
				statement.executeBatch();
				
				state = true;
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return state;
	}
	
	
}
