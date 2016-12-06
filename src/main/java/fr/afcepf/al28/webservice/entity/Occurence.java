/**
 * 
 */
package fr.afcepf.al28.webservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author stagiaire
 *
 */
@Entity
@Table(name = "occurence", catalog = "bdservicefournisseur")
public class Occurence implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_occurence;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_film", nullable = false)
	private Film film;
	
	@Column(name = "date_seance", nullable = false)
	private Date date_seance;
	
	@Column(name = "nbre_places_tot", nullable = false)
	private Integer nbre_places_tot;
	
	@Column(name = "nbre_places_dispo", nullable = false)
	private Integer nbre_places_dispo;
	
	@Column(name = "lieu", nullable = false, length = 450)
	private String lieu;

	public Integer getId_occurence() {
		return id_occurence;
	}

	public void setId_occurence(Integer id_occurence) {
		this.id_occurence = id_occurence;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Date getDate_seance() {
		return date_seance;
	}

	public void setDate_seance(Date date_seance) {
		this.date_seance = date_seance;
	}

	public Integer getNbre_places_tot() {
		return nbre_places_tot;
	}

	public void setNbre_places_tot(Integer nbre_places_tot) {
		this.nbre_places_tot = nbre_places_tot;
	}

	public Integer getNbre_places_dispo() {
		return nbre_places_dispo;
	}

	public void setNbre_places_dispo(Integer nbre_places_dispo) {
		this.nbre_places_dispo = nbre_places_dispo;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Occurence(Integer id_occurence, Film film, Date date_seance, Integer nbre_places_tot,
			Integer nbre_places_dispo, String lieu) {
		super();
		this.id_occurence = id_occurence;
		this.film = film;
		this.date_seance = date_seance;
		this.nbre_places_tot = nbre_places_tot;
		this.nbre_places_dispo = nbre_places_dispo;
		this.lieu = lieu;
	}

	public Occurence() {
		super();
	}

	@Override
	public String toString() {
		return "Occurence [id_occurence=" + id_occurence + ", film=" + film + ", date_seance=" + date_seance
				+ ", nbre_places_tot=" + nbre_places_tot + ", nbre_places_dispo=" + nbre_places_dispo + ", lieu=" + lieu
				+ "]";
	}
	
	
	
}
