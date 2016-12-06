package fr.afcepf.al28.webservice.soap;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import fr.afcepf.al28.webservice.Dto.OccurenceDto;

@WebService
public interface IserviceResaSoapApi {
	Integer nbrPlaceDispoByIdOccurence(@WebParam(name = "idOccurence") Integer idOccurence);
	List<OccurenceDto> getAllOccurenceByIdFilm(@WebParam(name = "idFilm")Integer idFilm);
	String reserverLesPlaces(@WebParam(name="listACheker")String listACheker);
	String annulerLesPlacesReservees(@WebParam(name="listAAnnuler")String listAAnnuler);

}
