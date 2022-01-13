package com.formulario.request.mapping;

public class FormularioRequestMapping {

	private Long documento;
	private String email;
	private String comentario;
	private Long marcapc;
	private String idUsuario;

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

	public Long getMarcapc() {
		return marcapc;
	}

	public void setMarcapc(Long marcapc) {
		this.marcapc = marcapc;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

}
