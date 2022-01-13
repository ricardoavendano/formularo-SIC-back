package com.formulario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.formulario.datatransfer.EncuestaDTO;
import com.formulario.datatransfer.MarcaPcDTO;
import com.formulario.request.mapping.FormularioRequestMapping;

import fj.data.Either;

@Service
public interface EncuestaService {

	public Either<Exception, List<EncuestaDTO>> listarEncuesta(String idUsuario);

	public Either<Exception, String> guardarEncuesta(FormularioRequestMapping compraRequestMapping);

	public Either<Exception, String> eliminarRegistro(Long idEncuesta);

	public Either<Exception, List<MarcaPcDTO>> listarMarcas();
}
