package com.formulario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formulario.datatransfer.EncuestaDTO;
import com.formulario.datatransfer.Error;
import com.formulario.datatransfer.MarcaPcDTO;
import com.formulario.datatransfer.UsuarioDTO;
import com.formulario.exception.UsuarioYaExisteException;
import com.formulario.request.mapping.FormularioRequestMapping;
import com.formulario.service.EncuestaService;
import com.formulario.service.ErrorService;
import com.formulario.service.UsuarioService;

import fj.data.Either;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping
@RestController
@Controller
public class EncuestaController {

	@Autowired
	private EncuestaService encuestaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ErrorService errorService;

	@PostMapping(value = "/login")
	public ResponseEntity<?> validarLogin(@RequestBody UsuarioDTO usuarioDTO) {

		Either<Exception, UsuarioDTO> resultEither = usuarioService.validarAutenticacionUsuario(usuarioDTO);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/login/create")
	public ResponseEntity<?> createLogin(@RequestBody UsuarioDTO usuarioDTO) {

		Either<Exception, UsuarioDTO> resultEither = usuarioService.validarAutenticacionUsuario(usuarioDTO);

		if (resultEither.isRight()) {
			try {
				throw new UsuarioYaExisteException();
			} catch (Exception e) {
				return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		if (resultEither.isLeft()) {
			Either<Exception, UsuarioDTO> result = usuarioService.crearUsuario(usuarioDTO);
			if (result.isRight()) {
				return new ResponseEntity<>(result.right().value(), HttpStatus.OK);
			}

			Error error = errorService.getError(result.left().value());
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return null;
	}

	@PostMapping(value = "/agregar/encuesta")
	public ResponseEntity<?> agregarEncuesta(@RequestBody FormularioRequestMapping formularioRequestMapping) {

		Either<Exception, String> resultEither = encuestaService.guardarEncuesta(formularioRequestMapping);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/listar/encuesta/usuario/{idUsuario}")
	public ResponseEntity<?> listarEncuesta(@PathVariable String idUsuario) {

		Either<Exception, List<EncuestaDTO>> resultEither = encuestaService.listarEncuesta(idUsuario);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/listar/encuesta/eliminar/{id}")
	public ResponseEntity<?> eliminarEncuesta(@PathVariable Long id) {

		Either<Exception, String> resultEither = encuestaService.eliminarRegistro(id);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/listar/encuesta/marcas")
	public ResponseEntity<?> listarMarcas() {

		Either<Exception, List<MarcaPcDTO>> resultEither = encuestaService.listarMarcas();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
