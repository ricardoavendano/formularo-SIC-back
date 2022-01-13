package com.formulario.service;

import org.springframework.stereotype.Service;

import com.formulario.datatransfer.UsuarioDTO;

import fj.data.Either;

@Service
public interface UsuarioService {

	public Either<Exception, UsuarioDTO> validarAutenticacionUsuario(UsuarioDTO usuarioDTO);

	public Either<Exception, UsuarioDTO> crearUsuario(UsuarioDTO usuarioDTO);
}
