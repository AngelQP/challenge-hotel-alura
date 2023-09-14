package com.alura.util;

import com.alura.model.Huesped;
import com.alura.model.Reserva;

public interface ObservadorDatos {

	void actualizarDatos(Reserva reserva);
	
	void actualizarDatos(Huesped huesped);

}
