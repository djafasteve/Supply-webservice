package fr.afcepf.al28.webservice.soap;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.afcepf.al28.webservice.Dto.OccurenceDto;
import fr.afcepf.al28.webservice.entity.Occurence;
import fr.afcepf.al28.webservice.service.ServiceResaApi;

@WebService(endpointInterface="fr.afcepf.al28.webservice.soap.IserviceResaSoapApi")
@Service
public class ServiceResaSoapImpl implements IserviceResaSoapApi {

	@Autowired
	private ServiceResaApi serviceResaImpl;


	@Override
	public Integer nbrPlaceDispoByIdOccurence(Integer idOccurence) {

		return serviceResaImpl.nbrPlaceDispoByIdOccurence(idOccurence);
	}

	@Override
	public List<OccurenceDto> getAllOccurenceByIdFilm(Integer idFilm) {

		List<Occurence> occurences = serviceResaImpl.getAllOccurencesByIdFilm(idFilm);

		List<OccurenceDto> occurencesDto = new ArrayList<OccurenceDto>();

		for (Occurence o : occurences) {
			OccurenceDto oDto = new OccurenceDto();
			oDto.setId_occurence(o.getId_occurence());
			oDto.setNbPlaces(o.getNbre_places_dispo());
			occurencesDto.add(oDto);
		}

		return occurencesDto;
	}

	@Override
	public String reserverLesPlaces(String listACheker) {
		// [{"id_occurence":1,"nbPlaces":1500},{"id_occurence":2,"nbPlaces":1500}]
		int echecResa = 0;

		//****************************DECODAGE string to list<obj>***********************
		ObjectMapper mapper = new ObjectMapper();

		//BufferedReader br = null;
		String jsonInput = listACheker;
		String json = "";
		List<OccurenceDto> listAChekerOutput = null;

		System.out.println(jsonInput);
		System.out.println("***********************avant jsoninput apres*************************************");
		json = jsonInput.replaceAll("\"", "\\\"");
		System.out.println(json);

		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			listAChekerOutput = mapper.readValue(json, new TypeReference<List<OccurenceDto>>() {});
			System.out.println("************list a checher size***********" + listAChekerOutput.size());
			for (OccurenceDto b2 : listAChekerOutput){
				System.out.println("****occurence dans la list a checher ****" + b2);
			}
				
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//*******************************************************************************

		for (OccurenceDto o : listAChekerOutput) {
			//appeller methode reserverPlaceByOccurence(o.getId_occurence(), o.getNbPlaces()) et retourne nombre de place à Checker 
			// soit rest le meme valeur quand dispo, soit valeur modifier en nbre restant en cas echec
			int nbPlaceAChecherApresVerifier = serviceResaImpl.reserverPlaceByOccurence(o.getId_occurence(), o.getNbPlaces());
			System.out.println("nbPlaceAChecherApresVerifier****" +nbPlaceAChecherApresVerifier + "***nbPlace a checher ** " +o.getNbPlaces());
			// si nbplace de ce occurence n'est dispo (nbplace est modifier), alors modifier la list a checher
			if (nbPlaceAChecherApresVerifier != o.getNbPlaces()) {
				System.out.println(o.getId_occurence() + "******************occurence pas dispo ***" +  o.getNbPlaces() +"**echecResa***" + echecResa );
				o.setNbPlaces(serviceResaImpl.reserverPlaceByOccurence(o.getId_occurence(), o.getNbPlaces()));
				echecResa = echecResa + 1;
				
			} else {
				// pour les places qui sont deja reserver lors moment checher dispo, si il y a echec dans la list, alors annuler resa.
				if (echecResa > 0) {
					System.out.println(o.getId_occurence() + "********************Annuler ***" +  o.getNbPlaces() +"**raison echec***" + echecResa);
					serviceResaImpl.annulerPlaceReserveByOccurence(o.getId_occurence(), o.getNbPlaces());
				}
			}
		}
	
		try {	
			//Convert to Json String
			listACheker = mapper.writeValueAsString(listAChekerOutput);
			System.out.println(listACheker);	
		} catch (JsonGenerationException e) {	
			e.printStackTrace();
		} catch (JsonMappingException e) {	
			e.printStackTrace();
		} catch (IOException e) {	
			e.printStackTrace();
		}
		
		System.out.println("**********echec nb******" +echecResa);
		if (echecResa == 0) {
			listACheker = "reserver";
		}

		return listACheker;
	}

	@Override
	public String annulerLesPlacesReservees(String listAAnnuler) {

		//****************************DECODAGE string to list<obj>***********************
		ObjectMapper mapper = new ObjectMapper();

		BufferedReader br = null;
		String jsonInput = listAAnnuler;
		String json = "";
		List<OccurenceDto> listAAnnulerOutput = null;

		System.out.println("*****jsonInput listAnnuler string *****"+jsonInput);
		json = jsonInput.replaceAll("\"", "\\\"");
		System.out.println("*****json former listAnnuler string *****"+ json);

		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			listAAnnulerOutput = mapper.readValue(json, new TypeReference<List<OccurenceDto>>() {});
			for (OccurenceDto b2 : listAAnnulerOutput)
				System.out.println("****occurence dans la list a annuler ****"+b2);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//*******************************************************************************

		for (OccurenceDto o : listAAnnulerOutput) {
			System.out.println(o.getId_occurence() + " occurence************** service annuler ************nbplace " +  o.getNbPlaces());
			serviceResaImpl.annulerPlaceReserveByOccurence(o.getId_occurence(), o.getNbPlaces());
			
		}
		return "annuler";
	}

}
