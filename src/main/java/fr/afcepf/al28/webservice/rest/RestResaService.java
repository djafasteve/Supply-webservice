package fr.afcepf.al28.webservice.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al28.webservice.Dto.OccurenceDto;
import fr.afcepf.al28.webservice.entity.Occurence;
import fr.afcepf.al28.webservice.service.ServiceResaApi;

@Component
@Path("/reservation")
@Produces("application/json")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class RestResaService {

	@Autowired
	private ServiceResaApi serviceResaImpl;
	

	/**
	 * 
	 * @param idOccurence
	 * @return
	 */
	@GET
	@Path("/nbrPlaceDispo/{idOccur}")
	public Integer nbrPlaceDispoByIdOccurence(@PathParam(value = "idOccur") Integer idOccurence) {

		return serviceResaImpl.nbrPlaceDispoByIdOccurence(idOccurence);
	}
	
	@GET
	@Path("/allOccurences/{idFilm}")
	public List<OccurenceDto> getAllOccurenceByIdFilm(@PathParam(value = "idFilm")Integer idFilm){
		
		List<Occurence> occurences = serviceResaImpl.getAllOccurencesByIdFilm(idFilm);
		
		List<OccurenceDto> occurenceDtos = new ArrayList<OccurenceDto>();
		
		for(Occurence o : occurences){
			OccurenceDto oDto = new OccurenceDto();
			oDto.setId_occurence(o.getId_occurence());
			oDto.setNbPlaces(o.getNbre_places_dispo());
			occurenceDtos.add(oDto);
		}
		
		
		return occurenceDtos;
		
	}

	// verifier list des occurences avec leurs nombre place demander, et si tous
	// dispo alors reserver place et
	// retourner une list de Echec en null, si reservation echec alor retourner
	// listAcheker contient list occurences
	// et leur nombre place modifier en nombre restant fournisseur.
	// [{"id_occurence":1,"nbPlaces":1500},{"id_occurence":2,"nbPlaces":1500}]
	@POST
	@Path("/reserverPlace")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<OccurenceDto> reserverLesPlaces(List<OccurenceDto> listACheker) {
		int echecResa = 0;
		
		for (OccurenceDto o : listACheker) {
			
			if (serviceResaImpl.reserverPlaceByOccurence(o.getId_occurence(), o.getNbPlaces()) 
					!= o.getNbPlaces()) {
				o.setNbPlaces(serviceResaImpl.reserverPlaceByOccurence(o.getId_occurence(), o.getNbPlaces()));
				echecResa = echecResa + 1;
			}else{
				if(echecResa > 0){
				serviceResaImpl.annulerPlaceReserveByOccurence(o.getId_occurence(), o.getNbPlaces());
				}
			}
		}

		if (echecResa == 0) {
			listACheker = null;
		}

		return listACheker;
	}

	@POST
	@Path("/annulerPlace")
	public void annulerLesPlacesReservees(List<OccurenceDto> listAAnnuler) {
		for (OccurenceDto o : listAAnnuler) {
			serviceResaImpl.annulerPlaceReserveByOccurence(o.getId_occurence(), o.getNbPlaces());

		}
	}
}
