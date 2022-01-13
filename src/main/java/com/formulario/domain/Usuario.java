package com.formulario.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 5106190012848293912L;

	@Id
	@Column(nullable = false, name = "idusuario")
	private String idUsuario;

	@Column(nullable = false, name = "password")
	private String password;

	@Column(nullable = false, name = "intento")
	private int intento;

	@OneToMany(mappedBy = "idUsuarioPK")
	private List<Encuesta> encuestaList;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@XmlTransient
	public List<Encuesta> getEncuestaList() {
		return encuestaList;
	}

	public void setEncuestaList(List<Encuesta> encuestaList) {
		this.encuestaList = encuestaList;
	}

	public int getIntento() {
		return intento;
	}

	public void setIntento(int intento) {
		this.intento = intento;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idUsuario != null ? idUsuario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.idUsuario == null && other.idUsuario != null)
				|| (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entity.Usuario[ idusuario=" + idUsuario + " ]";
	}
}
