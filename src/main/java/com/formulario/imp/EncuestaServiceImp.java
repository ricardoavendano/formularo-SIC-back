package com.formulario.imp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formulario.adapter.EncuestaAdapter;
import com.formulario.datatransfer.EncuestaDTO;
import com.formulario.datatransfer.MarcaPcDTO;
import com.formulario.domain.Encuesta;
import com.formulario.domain.MarcaPc;
import com.formulario.domain.Usuario;
import com.formulario.exception.NoExistenEncuestaException;
import com.formulario.repository.EncuestaRepository;
import com.formulario.repository.MarcaRepository;
import com.formulario.request.mapping.FormularioRequestMapping;
import com.formulario.service.EncuestaService;

import fj.data.Either;

@Service
public class EncuestaServiceImp implements EncuestaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EncuestaServiceImp.class);

	@Autowired
	private EncuestaRepository encuestaRepository;

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private EncuestaAdapter encuestaAdapter;

	@Transactional
	public Either<Exception, String> guardarEncuesta(FormularioRequestMapping formularioRequestMapping) {
		try {

			Encuesta encuesta = new Encuesta();
			encuesta.setIdencuesta(0L);
			encuesta.setDocumento(formularioRequestMapping.getDocumento());
			encuesta.setEmail(formularioRequestMapping.getEmail());
			encuesta.setComentario(formularioRequestMapping.getComentario());
			encuesta.setFechaRespuesta(LocalDateTime.now());

			MarcaPc marcaPc = new MarcaPc();
			marcaPc.setIdmarcapc(formularioRequestMapping.getMarcapc());
			encuesta.setIdMarcapcPK(marcaPc);

			Usuario usuario = new Usuario();
			usuario.setIdUsuario(formularioRequestMapping.getIdUsuario());
			encuesta.setIdUsuarioPK(usuario);

			encuestaRepository.save(encuesta);
			return Either.right("200");
		} catch (Exception e) {

			return Either.left(e);
		}
	}

	@Transactional
	public Either<Exception, List<EncuestaDTO>> listarEncuesta(String idUsuario) {
		try {
			List<Encuesta> listEncuesta = (List<Encuesta>) encuestaRepository.findAll();

			if (listEncuesta.isEmpty()) {
				throw new NoExistenEncuestaException();
			} else {

				listEncuesta = listEncuesta.stream().filter(p -> p.getIdUsuarioPK().getIdUsuario().equals(idUsuario))
						.collect(Collectors.toList());

				List<EncuestaDTO> listEncuestaDTO = encuestaAdapter.encuestaListAdapter(listEncuesta);
				if (!listEncuestaDTO.isEmpty()) {
					return Either.right(listEncuestaDTO);
				} else {
					throw new NoExistenEncuestaException();
				}
			}

		} catch (NoExistenEncuestaException e) {
			LOGGER.error("CompraUsuarioServiceImp:listarCompraPendienteUsuario", e);
			return Either.left(e);
		} catch (Exception e) {

			return Either.left(e);
		}
	}

	@Transactional
	public Either<Exception, String> eliminarRegistro(Long idEncuesta) {
		try {
			Optional<Encuesta> encuesta = encuestaRepository.findById(idEncuesta);

			if (encuesta.isPresent()) {
				encuestaRepository.delete(encuesta.get());
				return Either.right("200");
			}
		} catch (Exception e) {

			return Either.left(e);
		}

		return Either.right("");
	}

	@Override
	public Either<Exception, List<MarcaPcDTO>> listarMarcas() {
		try {

			List<MarcaPc> marcaPc = (List<MarcaPc>) marcaRepository.findAll();
			List<MarcaPcDTO> marcaPcList = new ArrayList<>();

			for (MarcaPc marca : marcaPc) {
				MarcaPcDTO marcaPcDTO = new MarcaPcDTO();
				marcaPcDTO.setIdMarcaPc(marca.getIdmarcapc());
				marcaPcDTO.setMarca(marca.getMarca());

				marcaPcList.add(marcaPcDTO);
			}
			return Either.right(marcaPcList);
		} catch (Exception e) {
			return Either.left(e);
		}
	}
}
