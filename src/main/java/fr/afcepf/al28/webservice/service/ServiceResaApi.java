package fr.afcepf.al28.webservice.service;


import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.afcepf.al28.webservice.entity.Occurence;

public interface ServiceResaApi {
	public Integer nbrPlaceDispoByIdOccurence(Integer idOccurence);
	public int reserverPlaceByOccurence(Integer idOccurence, Integer nbrPlaceAReserver);
	public boolean annulerPlaceReserveByOccurence(Integer idOccurence, Integer nbrPlaceAAnnler);
	public List<Occurence> getAllOccurencesByIdFilm(Integer idFilm); 
}
