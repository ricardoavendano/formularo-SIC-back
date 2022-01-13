package com.formulario.imp;

import java.util.Base64;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formulario.datatransfer.UsuarioDTO;
import com.formulario.domain.Usuario;
import com.formulario.exception.AutenticacionFallidaException;
import com.formulario.exception.UsuarioNoExisteException;
import com.formulario.repository.UsuarioRepository;
import com.formulario.service.UsuarioService;

import fj.data.Either;

@Service
public class UsuarioServiceImp implements UsuarioService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImp.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Either<Exception, UsuarioDTO> validarAutenticacionUsuario(UsuarioDTO usuarioDTO) {
		try {
			Optional<Usuario> usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario());

			if (!usuario.isPresent()) {
				throw new UsuarioNoExisteException();
			} else {

				if (usuario.get().getIntento() < 3) {

					String encodedString = Base64.getEncoder().encodeToString(usuarioDTO.getPassword().getBytes());
					if (encodedString.equals(usuario.get().getPassword())) {

						usuario.get().setIntento(0);
						usuarioRepository.save(usuario.get());
						usuarioDTO.setPassword(usuario.get().getPassword());
						return Either.right(usuarioDTO);
					} else {
						usuario.get().setIntento(usuario.get().getIntento() + 1);
						usuarioRepository.save(usuario.get());
						throw new AutenticacionFallidaException();
					}
				} else {
					throw new AutenticacionFallidaException();
				}

			}

		} catch (AutenticacionFallidaException | UsuarioNoExisteException e) {
			LOGGER.error("UsuarioServiceImp:validarAutenticacionUsuario", e);
			return Either.left(e);

		} catch (Exception e) {

			return Either.left(e);
		}
	}

	public Either<Exception, UsuarioDTO> crearUsuario(UsuarioDTO usuarioDTO) {
		try {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(usuarioDTO.getIdUsuario());

			String encodedString = Base64.getEncoder().encodeToString(usuarioDTO.getPassword().getBytes());
			usuario.setPassword(encodedString);
			usuarioRepository.save(usuario);
			return Either.right(usuarioDTO);
		} catch (Exception e) {

			return Either.left(e);
		}
	}
}
