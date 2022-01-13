package com.formulario.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.formulario.datatransfer.Error;
import com.formulario.exception.AutenticacionFallidaException;
import com.formulario.exception.NoExistenEncuestaException;
import com.formulario.exception.UsuarioNoEncontradoException;
import com.formulario.exception.UsuarioNoExisteException;

@Service
public class ErrorService {

	public Error getError(Exception e) {

		if (e instanceof UsuarioNoEncontradoException) {

			return new Error("UsuarioNoEncontradoException001", "No hay usuarios registrados");

		}
		if (e instanceof NoExistenEncuestaException) {

			return new Error("NoExistenComprasPendientesException001", "No hay compras pendientes en el carrito");

		}

		if (e instanceof UsuarioNoExisteException) {

			return new Error("UsuarioNoExisteException001", "Usuario no encontrado");

		}
		if (e instanceof AutenticacionFallidaException) {

			return new Error("AutenticacionFallidaException001", "Autenticacion fallida, vuelva a intentarlo");

		}

		Map<String, String> params = new HashMap<>();
		params.put("Exception", e.getClass() + "-" + e.getMessage());
		params.put("Time", LocalDateTime.now().toString());

		return new Error("Libro001", "Unknown Error");
	}
}
