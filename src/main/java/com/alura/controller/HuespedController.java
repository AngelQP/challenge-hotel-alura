package com.alura.controller;

import java.util.List;

import com.alura.dao.HuespedDao;
import com.alura.model.Huesped;
import com.alura.util.ConnectionFactory;

public class HuespedController {
	
	private HuespedDao huespedDao;

	public HuespedController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.huespedDao = new HuespedDao(factory.recuperaConexion());
	}
	
	/* CRUD */
	
	// GUARDAR
	public boolean guardar(Huesped huesped) {
		return huespedDao.guardar(huesped);
	}

	public boolean update(Huesped huesped) {
		return huespedDao.update(huesped);
	}

	public List<Huesped> listar(String busqueda) {
		return huespedDao.listar(busqueda);
	}
	
	

}
