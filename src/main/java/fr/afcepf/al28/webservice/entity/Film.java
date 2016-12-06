package fr.afcepf.al28.webservice.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "film", catalog = "bdservicefournisseur")
public class Film implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_film;
	
	@Column(name = "titre", nullable = false, length = 50)
	private String titre;

	@Column(name = "descriptif", length = 300)
	private String descriptif;

	@Column(name = "date_debut", nullable = false)
	private Date date_debut;

	@Column(name = "date_fin")
	private Date date_fin;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "film")
	private List<Occurence> occurences = new ArrayList<Occurence>();

	public Integer getId_film() {
		return id_film;
	}

	public void setId_film(Integer id_film) {
		this.id_film = id_film;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public List<Occurence> getOccurences() {
		return occurences;
	}

	public void setOccurences(List<Occurence> occurences) {
		this.occurences = occurences;
	}

	public Film(Integer id_film, String titre, String descriptif, Date date_debut, Date date_fin) {
		super();
		this.id_film = id_film;
		this.titre = titre;
		this.descriptif = descriptif;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
	}

	public Film() {
		super();
	}

	@Override
	public String toString() {
		return "Film [id_film=" + id_film + ", titre=" + titre + ", descriptif=" + descriptif + ", date_debut="
				+ date_debut + ", date_fin=" + date_fin + "]";
	}	
}
