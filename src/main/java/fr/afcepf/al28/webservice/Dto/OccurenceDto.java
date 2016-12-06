package fr.afcepf.al28.webservice.Dto;

import java.io.Serializable;

public class OccurenceDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_occurence;
	private int nbPlaces;
	public int getId_occurence() {
		return id_occurence;
	}
	public void setId_occurence(int id_occurence) {
		this.id_occurence = id_occurence;
	}
	public int getNbPlaces() {
		return nbPlaces;
	}
	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	public OccurenceDto(int id_occurence, int nbPlaces) {
		super();
		this.id_occurence = id_occurence;
		this.nbPlaces = nbPlaces;
	}
	public OccurenceDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OccurenceDto [id_occurence=" + id_occurence + ", nbPlaces=" + nbPlaces + "]";
	}
	
}
