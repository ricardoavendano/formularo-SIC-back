package com.formulario.repository;

import org.springframework.data.repository.CrudRepository;

import com.formulario.domain.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{

}
