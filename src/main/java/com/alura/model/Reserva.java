package com.alura.model;

import java.sql.Date;
import java.time.LocalDate;

import com.alura.util.ChangeDate;

public class Reserva {

	private Integer id;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Integer valor;
	private String formaPago;
	
	public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, Integer valor, String formaPago) {
		this.id = id;
		this.fechaEntrada = ChangeDate.formatDateToLocalDate(fechaEntrada);
		this.fechaSalida = ChangeDate.formatDateToLocalDate(fechaSalida);
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, int valor, String formaPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}
	
	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}
	
	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public Integer getValor() {
		return valor;
	}
	
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	public String getFormaPago() {
		return formaPago;
	}
	
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", valor="
				+ valor + ", formaPago=" + formaPago + "]";
	}
	
	
	
	
}
