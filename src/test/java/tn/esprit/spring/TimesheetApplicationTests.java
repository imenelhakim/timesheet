package tn.esprit.spring;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.controller.ControllerEmployeImpl;
import tn.esprit.spring.controller.ControllerEntrepriseImpl;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;

@SpringBootTest
class TimesheetApplicationTests {
	private static final Logger l = LogManager.getLogger(TimesheetApplication.class);

	// private static final Logger l = Logger.getLogger(TimesheetApplication.class);
	@Autowired
	ControllerEntrepriseImpl entrepriseControl;
	@Autowired
	ControllerEmployeImpl employeControl;
	@Autowired
	EmployeRepository employeRepository;

	@Transactional
	@Test
	void testajouterDepartementAEntreprise() {

		l.info("lancer  la methode ajouter departement");
		l.debug("je vais lancer la methode save du departement");
		Entreprise ssiiConsulting = new Entreprise("SSII Consulting", "Cite El Ghazela");
		Departement depTelecom = new Departement("Telecom");
		Departement depRH = new Departement("RH");
		ssiiConsulting.addDepartement(depRH);
		ssiiConsulting.addDepartement(depTelecom);
		l.debug("je viens de finir save de departement");
		l.info("fin de  la methode ajouter departement");
	}

	@Transactional
	@Test
	void affecterDepartementAEntreprise() {

		l.info("lancer  la methode affectation departement a entreprise");
		l.debug("je vais lancer la recherche de l'entreprise par id ");

		Entreprise ssiiConsulting = new Entreprise("SSII Consulting", "Cite El Ghazela");
		Departement depTelecom = new Departement("Telecom");
		Departement depRH = new Departement("RH");
		int ssiiConsultingId = entrepriseControl.ajouterEntreprise(ssiiConsulting);
		ssiiConsulting.setId(ssiiConsultingId);
		depTelecom.setEntreprise(ssiiConsulting);
		int depTelecomId = entrepriseControl.ajouterDepartement(depTelecom);
		depRH.setEntreprise(ssiiConsulting);
		int depRhId = entrepriseControl.ajouterDepartement(depRH);

		entrepriseControl.affecterDepartementAEntreprise(depTelecomId, ssiiConsultingId);
		entrepriseControl.affecterDepartementAEntreprise(depRhId, ssiiConsultingId);
		l.debug("je viens de faire l'update l'update de l'ntreprise et l'enregistré");
		l.info("fin de   la methode affectation departement a entreprise");
	}

	@Transactional
	@Test
	void getDepartementsNamesByEntreprise() {

		l.info("lancer  la methode get all department names by entreprise");
		l.debug("lancer  la recherche de l entreprise par id");
		Entreprise ssiiConsulting = new Entreprise("SSII Consulting", "Cite El Ghazela");
		int ssiiConsultingId = entrepriseControl.ajouterEntreprise(ssiiConsulting);
		l.debug("je viens de trouver l entreprise" + ssiiConsultingId);

		List<String> departements =
				entrepriseControl.getAllDepartementsNamesByEntreprise(ssiiConsultingId);
		for (String departementName : departements) {
			l.info(departementName);
		}
		l.debug("je viens de remplir le tableau depNames");
		l.info("fin de   la methode get all department names by entreprise");

	}

	@Transactional
	@Test
	void mettreAjourEmailByEmployeIdJPQL() {

		l.info("lancer la methode mettreAjourEmailByEmployeIdJPQL");
		l.debug("je vais mettre ajour l'email d'un employe a traver son id");
		Employe employe = new Employe("mohamed", "mansour", "mohamedmansour@email.com",
				"123ingenieur", true, Role.INGENIEUR);
		int employeId = employeControl.ajouterEmploye(employe);
		Assert.assertNotNull(employeControl.getEmployePrenomById(employeId));
		employeRepository.mettreAjourEmailByEmployeIdJPQL("raed@gmail.com", employeId);
		l.debug("je viens de mettre ajour l'email d'un employe a traver son id");
		l.info("fin de  la methode  mettreAjourEmailByEmployeIdJPQL");

	}

}
