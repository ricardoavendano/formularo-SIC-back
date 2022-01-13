package com.formulario.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "marcapc")
public class MarcaPc implements Serializable {

	private static final long serialVersionUID = -8137769589372309692L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "idmarcapc")
	private Long idmarcapc;

	@Column(nullable = false, name = "marca")
	private String marca;

	@OneToMany(mappedBy = "idMarcapcPK")
	private List<Encuesta> encuestaList;

	public Long getIdmarcapc() {
		return idmarcapc;
	}

	public void setIdmarcapc(Long idmarcapc) {
		this.idmarcapc = idmarcapc;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@XmlTransient
	public List<Encuesta> getEncuestaList() {
		return encuestaList;
	}

	public void setEncuestaList(List<Encuesta> encuestaList) {
		this.encuestaList = encuestaList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idmarcapc != null ? idmarcapc.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MarcaPc)) {
			return false;
		}
		MarcaPc other = (MarcaPc) object;
		if ((this.idmarcapc == null && other.idmarcapc != null)
				|| (this.idmarcapc != null && !this.idmarcapc.equals(other.idmarcapc))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entity.marcapc[ idmarcapc=" + idmarcapc + " ]";
	}
}
