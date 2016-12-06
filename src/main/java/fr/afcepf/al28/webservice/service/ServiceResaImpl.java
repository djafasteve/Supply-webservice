package fr.afcepf.al28.webservice.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al28.webservice.api.IDaoFilm;
import fr.afcepf.al28.webservice.entity.Occurence;

@Service
@Transactional
public class ServiceResaImpl implements ServiceResaApi{

	@Autowired
	private IDaoFilm daoFilm;

	@Override
	public Integer nbrPlaceDispoByIdOccurence(Integer idOccurence) {
		
		return daoFilm.getNombrePlacesDispoByIdOccurence(idOccurence);
	}

	@Override
	public int reserverPlaceByOccurence(Integer idOccurence, Integer nbrPlaceAReserver) {
		
		nbrPlaceAReserver =  daoFilm.decrementerNbrePlacesDispoByIdOccurence(idOccurence, nbrPlaceAReserver);
		System.out.println(idOccurence + "**********************reserver ***" +  nbrPlaceAReserver);
		 return nbrPlaceAReserver;
	}

	@Override
	public boolean annulerPlaceReserveByOccurence(Integer idOccurence, Integer nbrPlaceAAnnler) {
		System.out.println(idOccurence + "**********************annuler ***" +  nbrPlaceAAnnler);
		return daoFilm.incrementerNbrePlacesDispoByIdOccurence(idOccurence, nbrPlaceAAnnler);
	}

	@Override
	public List<Occurence> getAllOccurencesByIdFilm(Integer idFilm) {
		
		return daoFilm.getOccurencesByIdFilm(idFilm);
	}
	
}
