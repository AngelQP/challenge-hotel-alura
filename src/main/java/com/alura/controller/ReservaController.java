package com.alura.controller;

import com.alura.dao.ReservaDao;
import com.alura.model.Reserva;
import com.alura.util.ConnectionFactory;

public class ReservaController {

	private ReservaDao reservaDao;
	
	public static Reserva reservaBackup = null;

	public ReservaController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.reservaDao = new ReservaDao(factory.recuperaConexion());
	}
	
	// CRUD
	
	// Guardar	
	public void guardar(Reserva reserva) {
		reservaDao.guardar(reserva);
	}
	
	// Eliminar
	public int eliminar(Integer id) {
		return reservaDao.eliminar(id);
	}

	// Listar
	public Reserva listar(Integer busqueda) {
		return reservaDao.listar(busqueda);
	}

	public boolean update(Reserva reserva) {
		// TODO Auto-generated method stub
		return reservaDao.update(reserva);
	}

	public boolean delete(Integer id) {
		return reservaDao.delete(id);
		// TODO Auto-generated method stub
		
	}
	
	
}
