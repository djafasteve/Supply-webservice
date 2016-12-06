package fr.afcepf.al28.webservice.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al28.webservice.api.IDaoFilm;
import fr.afcepf.al28.webservice.entity.Film;
import fr.afcepf.al28.webservice.entity.Occurence;

@Service
@Transactional
public class DaoFilmImpl implements IDaoFilm {

	
	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	private static final String REQ_LIST_ALL_FILMS = "SELECT f FROM Film f";

	private static final String REQ_OCURENCES_BY_IDFILM = "SELECT o FROM Occurence o where o.film.id_film LIKE :paramId";

	private static final String REQ_ALL_OCCURENCES ="SELECT o FROM Occurence o";
	@Override
	public Film ajoutFilm(Film film) {

		sf.getCurrentSession().save(film);
		return film;
	}

	@Override
	public Occurence ajoutOccurence(Occurence occurence) {

		sf.getCurrentSession().save(occurence);
		return occurence;
	}

	@Override
	public Film findFilmById(Integer idFilm) {
		Film film = new Film();
		film = (Film) sf.getCurrentSession().get(Film.class, idFilm);
		return film;
	}

	@Override
	public Occurence findOccurenceById(Integer idOccurence) {
		Occurence occurence = new Occurence();
		occurence = (Occurence) sf.getCurrentSession().get(Occurence.class, idOccurence);

		return occurence;
	}

	@Override
	public List<Film> getAllFilms() {
		@SuppressWarnings("unchecked")
		List<Film> listeFilm = sf.getCurrentSession().createQuery(REQ_LIST_ALL_FILMS).list();
		return listeFilm;
	}
	
	@Override
	public List<Occurence> getAllOccurences() {
		@SuppressWarnings("unchecked")
		List<Occurence> listOccurence = sf.getCurrentSession().createQuery(REQ_ALL_OCCURENCES).list();
		return listOccurence;
	}
	@Override
	public List<Occurence> getOccurencesByIdFilm(Integer IdFilm) {
		@SuppressWarnings("unchecked")
		List<Occurence> listOccurence = sf.getCurrentSession().createQuery(REQ_OCURENCES_BY_IDFILM)
				.setParameter("paramId", IdFilm).list();
		return listOccurence;
	}

	@Override
	public Integer getNombrePlacesDispoByIdOccurence(Integer idOccurence) {
		Occurence o = findOccurenceById(idOccurence);
		int nombrePlaceDispo =0;
		if(o!=null){
		 nombrePlaceDispo = o.getNbre_places_dispo();
		}
		return nombrePlaceDispo;
	}

	@Override
	public boolean UpdateFilm(Film film) {
		boolean reussite = false;

		if (film != null) {
			try {

				sf.getCurrentSession().merge(film);
				reussite = true;
			} catch (Exception e) {
				reussite = false;
			}
		}
		return reussite;
	}

	@Override
	public boolean UpdateOccurence(Occurence occurence) {

		boolean reussite = false;

		if (occurence != null) {
			try {

				sf.getCurrentSession().merge(occurence);
				reussite = true;
			} catch (Exception e) {
				reussite = false;
			}
		}
		return reussite;
	}

	@Override
	public boolean incrementerNbrePlacesDispoByIdOccurence(Integer idOccurence, Integer nbPlacesEnPlus) {

		boolean reussite = false;

		try {

			Occurence occurence = findOccurenceById(idOccurence);
			if((occurence.getNbre_places_dispo() + nbPlacesEnPlus)<=occurence.getNbre_places_tot()){
			occurence.setNbre_places_dispo(occurence.getNbre_places_dispo() + nbPlacesEnPlus);
			UpdateOccurence(occurence);
			reussite = true;
			}else{
				reussite = false;
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			reussite = false;
		}
		return reussite;
	}

	// retourner nombre place demandé effecter par nombre restant en cas non dispo
	@Override
	public int decrementerNbrePlacesDispoByIdOccurence(Integer idOccurence, Integer nbPlacesEnMoins) {
		try {
			Occurence occurence = findOccurenceById(idOccurence);
			if(occurence.getNbre_places_dispo()>=nbPlacesEnMoins){
			occurence.setNbre_places_dispo(occurence.getNbre_places_dispo() - nbPlacesEnMoins);
			UpdateOccurence(occurence);
			}else{
				nbPlacesEnMoins = occurence.getNbre_places_dispo();
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nbPlacesEnMoins;
	}

	@Override
	public boolean checkValiditeCommande(String JsonCommande) {
		return false;
	}

	

}
