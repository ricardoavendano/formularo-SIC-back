package com.formulario.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.formulario.datatransfer.EncuestaDTO;
import com.formulario.datatransfer.MarcaPcDTO;
import com.formulario.datatransfer.UsuarioDTO;
import com.formulario.domain.Encuesta;

@Component
public class EncuestaAdapter {

	public List<EncuestaDTO> encuestaListAdapter(List<Encuesta> listEncuesta) {

		List<EncuestaDTO> encuestaListDTO = new ArrayList<>();

		for (Encuesta encuesta : listEncuesta) {

			EncuestaDTO compraDTO = encuestaAdapter(encuesta);
			encuestaListDTO.add(compraDTO);
		}

		return encuestaListDTO;
	}

	public EncuestaDTO encuestaAdapter(Encuesta encuesta) {

		EncuestaDTO encuestaDTO = new EncuestaDTO();

		encuestaDTO.setComentario(encuesta.getComentario());
		encuestaDTO.setDocumento(encuesta.getDocumento());
		encuestaDTO.setEmail(encuesta.getEmail());
		encuestaDTO.setIdEncuesta(encuesta.getIdencuesta());

		MarcaPcDTO marcaDTO = new MarcaPcDTO();
		marcaDTO.setIdMarcaPc(encuesta.getIdMarcapcPK().getIdmarcapc());
		marcaDTO.setMarca(encuesta.getIdMarcapcPK().getMarca());
		encuestaDTO.setIdMarcaPcpk(marcaDTO);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario(encuesta.getIdUsuarioPK().getIdUsuario());
		encuestaDTO.setIdUsuarioPK(usuarioDTO);

		return encuestaDTO;
	}

}
