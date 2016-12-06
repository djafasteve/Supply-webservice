package fr.afcepf.al28.webservice.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import fr.afcepf.al28.webservice.api.IDaoFilm;
import fr.afcepf.al28.webservice.entity.Film;
import fr.afcepf.al28.webservice.entity.Occurence;

@SessionScoped
@ManagedBean(name = "mBeanFilm")
public class GestionFilmManagedBean {

//	@ManagedProperty(value = "#{daoFilm}")
//	private IDaoFilm daoFilm;
	
	WebApplicationContext ctx =  FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
	IDaoFilm daoFilm = ctx.getBean(IDaoFilm.class);

	private List<Film> films = new ArrayList<Film>();

	public String getMessage() {
		return "salam";
	}

	// private Film film = new Film();
	//
	//
	// public Film getFilmById(Integer idFilm){
	// film = daoFilm.findFilmById(idFilm);
	// return film;
	// }

	public IDaoFilm getDaoFilm() {
		return daoFilm;
	}

	public void setDaoFilm(IDaoFilm daoFilm) {
		this.daoFilm = daoFilm;
	}

	public List<Occurence> findAllOccurences() {
		List<Occurence> occurences = new ArrayList<Occurence>();
		occurences = daoFilm.getAllOccurences();
		System.out.println("************taille list ***" + occurences.size());
		return occurences;

	}

	public boolean updateOccurence(Occurence occurence) {

		return daoFilm.UpdateOccurence(occurence);

	}

	public List<Film> findLesFilms() {
		films = daoFilm.getAllFilms();

		System.out.println("*********** siez film ****" + films.size());
		return films;

	}

	public List<Occurence> getOccurencesByIdFilm(Integer idFilm) {
		return daoFilm.getOccurencesByIdFilm(idFilm);
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

}
