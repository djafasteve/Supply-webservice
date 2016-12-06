package fr.afcepf.al28.webservice.daoimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.afcepf.al28.webservice.api.IDaoFilm;
import fr.afcepf.al28.webservice.entity.Film;
import fr.afcepf.al28.webservice.entity.Occurence;

public class TestApp {

	

	public static void main(String[] args) {
		
		Logger log = Logger.getLogger(TestApp.class);
		
		log.info("*********************début test****************************************");
		
		BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springData.xml");
		
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		IDaoFilm daoFilm = bf.getBean(IDaoFilm.class);
		try {
			Film film = new Film (null, "gonfuPanda", "Panda qui fait du gongfu, qui mange beaucoup", new Date(), new Date() );
			//daoFilm.ajoutFilm(film);
			Occurence o1 = new Occurence(null,film,new Date(),100,10,"paris MK2 chatelet les halles");
			Occurence o2 = new Occurence(null,film,new Date(),50,30,"paris UGC La Defence");
			//daoFilm.ajoutOccurence(o1);
			//daoFilm.ajoutOccurence(o2);
			
			List<Occurence> listo = daoFilm.getOccurencesByIdFilm(2);
			log.info("*********************nombre occurences by film********************" + listo.size());
			
			List<Film> listfilm = daoFilm.getAllFilms();
			
			for(Film f : listfilm){
				log.info("************ les fims qu'on a *********" + film.getTitre());
			}
			
			
			Film film2 = daoFilm.findFilmById(1);
			film2.setTitre("gongfu panda 2 ");
			daoFilm.UpdateFilm(film2);
			Film film3 = daoFilm.findFilmById(1);
			log.info("*************les film 1 new titre ****************" + film3.getTitre());
			
			
			log.info("*************nombre place dispo occurence 1 =  ****************" 
					+ daoFilm.getNombrePlacesDispoByIdOccurence(1));
			
			
			daoFilm.incrementerNbrePlacesDispoByIdOccurence(1, 10);
			Occurence overif = daoFilm.findOccurenceById(1);
			log.info("**nombre place dispo pour occurence 1 apres incrementer est  ********" + overif.getNbre_places_dispo());
			
			daoFilm.decrementerNbrePlacesDispoByIdOccurence(1, 10);
			Occurence overif2 = daoFilm.findOccurenceById(1);
			log.info("**nombre place dispo pour occurence 1 decrimenter********" + overif2.getNbre_places_dispo());
			log.info("********nombre demander apres effecter par nombre restant cas echec " + daoFilm.decrementerNbrePlacesDispoByIdOccurence(1, 20));
		
			List<Occurence>occurences = daoFilm.getAllOccurences();
			for(Occurence o:  occurences){
				log.info("********id occurence*****" + o.getId_occurence());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		log.info("*********************Fin test****************************************");
		}
		
	
	} 