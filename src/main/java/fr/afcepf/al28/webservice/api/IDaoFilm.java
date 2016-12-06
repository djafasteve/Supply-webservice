
package fr.afcepf.al28.webservice.api;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.afcepf.al28.webservice.entity.Film;
import fr.afcepf.al28.webservice.entity.Occurence;


public interface IDaoFilm {
	
	public Film ajoutFilm(Film film);
	public Occurence ajoutOccurence(Occurence occurence);
	public Film findFilmById(Integer idFilm);
	public Occurence findOccurenceById(Integer idOccurence);
	public List<Film> getAllFilms();
	public List<Occurence> getAllOccurences();
	public List<Occurence> getOccurencesByIdFilm(Integer idFilm);
	public Integer getNombrePlacesDispoByIdOccurence(Integer idOccurence);
	public boolean UpdateFilm(Film film);
	public boolean UpdateOccurence(Occurence occurence);
	
	public boolean incrementerNbrePlacesDispoByIdOccurence(Integer idOccurence,Integer nbPlacesEnPlus);
	public int decrementerNbrePlacesDispoByIdOccurence(Integer idOccurence, Integer nbPlacesEnMoins);
	public boolean checkValiditeCommande(String JsonCommande);
}
