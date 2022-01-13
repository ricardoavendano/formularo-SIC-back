package com.formulario.service.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.formulario.datatransfer.Error;
import com.formulario.exception.AutenticacionFallidaException;
import com.formulario.exception.NoExistenEncuestaException;
import com.formulario.exception.UsuarioNoEncontradoException;
import com.formulario.exception.UsuarioNoExisteException;
import com.formulario.service.ErrorService;

@RunWith(MockitoJUnitRunner.class)
public class ErrorServiceTest {

	@InjectMocks
	private ErrorService errorService;

	@Test
	public void UsuarioNoEncontradoException() {
		Error error = errorService.getError(new UsuarioNoEncontradoException());
		assertTrue(error.getCode().equals("UsuarioNoEncontradoException001"));
	}

	@Test
	public void noExistenComprasPendientesException() {
		Error error = errorService.getError(new NoExistenEncuestaException());
		assertTrue(error.getCode().equals("NoExistenComprasPendientesException001"));
	}

	@Test
	public void usuarioNoExisteException() {
		Error error = errorService.getError(new UsuarioNoExisteException());
		assertTrue(error.getCode().equals("UsuarioNoExisteException001"));
	}

	@Test
	public void autenticacionFallidaException() {
		Error error = errorService.getError(new AutenticacionFallidaException());
		assertTrue(error.getCode().equals("AutenticacionFallidaException001"));
	}

	@Test
	public void errorDesconocido() {
		Error error = errorService.getError(new Exception());

		assertTrue(error.getCode().equals("Libro001"));
		assertTrue(error.getDescription().equals("Unknown Error"));
	}

}
