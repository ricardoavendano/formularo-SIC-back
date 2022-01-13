package com.formulario.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "encuesta")
public class Encuesta implements Serializable {

	private static final long serialVersionUID = 1053005565218777998L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "idencuesta")
	private Long idencuesta;

	@Column(nullable = false, name = "documento")
	private Long documento;

	@Column(nullable = false, name = "email")
	private String email;

	@Column(nullable = false, name = "comentario")
	private String comentario;

	@Column(nullable = false, name = "fecharespuesta")
	private LocalDateTime fechaRespuesta;

	@JoinColumn(name = "IDMARCAPCPK", referencedColumnName = "IDMARCAPC", nullable = false)
	@ManyToOne(optional = false)
	private MarcaPc idMarcapcPK;

	@JoinColumn(name = "IDUSUARIOPK", referencedColumnName = "IDUSUARIO")
	@ManyToOne
	private Usuario idUsuarioPK;

	public Long getIdencuesta() {
		return idencuesta;
	}

	public void setIdencuesta(Long idencuesta) {
		this.idencuesta = idencuesta;
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

	public MarcaPc getIdMarcapcPK() {
		return idMarcapcPK;
	}

	public void setIdMarcapcPK(MarcaPc idMarcapcPK) {
		this.idMarcapcPK = idMarcapcPK;
	}

	public Usuario getIdUsuarioPK() {
		return idUsuarioPK;
	}

	public void setIdUsuarioPK(Usuario idUsuarioPK) {
		this.idUsuarioPK = idUsuarioPK;
	}

	public LocalDateTime getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(LocalDateTime fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idencuesta != null ? idencuesta.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Encuesta)) {
			return false;
		}
		Encuesta other = (Encuesta) object;
		if ((this.idencuesta == null && other.idencuesta != null)
				|| (this.idencuesta != null && !this.idencuesta.equals(other.idencuesta))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entity.encuesta[ idencuesta=" + idencuesta + " ]";
	}

}
