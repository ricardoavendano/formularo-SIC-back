package com.formulario.datatransfer;

public class EncuestaDTO {

	private Long idEncuesta;
	private Long documento;
	private String email;
	private String comentario;
	private MarcaPcDTO idMarcaPcpk;
	private UsuarioDTO idUsuarioPK;

	public Long getIdEncuesta() {
		return idEncuesta;
	}

	public void setIdEncuesta(Long idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public MarcaPcDTO getIdMarcaPcpk() {
		return idMarcaPcpk;
	}

	public void setIdMarcaPcpk(MarcaPcDTO idMarcaPcpk) {
		this.idMarcaPcpk = idMarcaPcpk;
	}

	public UsuarioDTO getIdUsuarioPK() {
		return idUsuarioPK;
	}

	public void setIdUsuarioPK(UsuarioDTO idUsuarioPK) {
		this.idUsuarioPK = idUsuarioPK;
	}

}
