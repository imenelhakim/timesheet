package tn.esprit.spring;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.controller.ControllerEmployeImpl;
import tn.esprit.spring.controller.ControllerEntrepriseImpl;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

@SpringBootTest
class TimesheetApplicationTests {
	private static final Logger l = Logger.getLogger(TimesheetApplication.class);
	@Autowired
	ControllerEntrepriseImpl entrepriseControl;
	@Autowired
	ControllerEmployeImpl employeControl;

	@Test
	void contextLoads() {

		Entreprise ssiiConsulting = new Entreprise("SSII Consulting", "Cite El Ghazela");
		Departement depTelecom = new Departement("Telecom");
		Departement depRH = new Departement("RH");
		ssiiConsulting.addDepartement(depRH);
		ssiiConsulting.addDepartement(depTelecom);
		int ssiiConsultingId = entrepriseControl.ajouterEntreprise(ssiiConsulting);
		ssiiConsulting.setId(ssiiConsultingId);
		depTelecom.setEntreprise(ssiiConsulting);
		int depTelecomId = entrepriseControl.ajouterDepartement(depTelecom);
		depRH.setEntreprise(ssiiConsulting);
		int depRhId = entrepriseControl.ajouterDepartement(depRH);

		entrepriseControl.affecterDepartementAEntreprise(depTelecomId, ssiiConsultingId);
		entrepriseControl.affecterDepartementAEntreprise(depRhId, ssiiConsultingId);
		List<String> departements =
				entrepriseControl.getAllDepartementsNamesByEntreprise(ssiiConsultingId);
		for (String departementName : departements) {
			l.info(departementName);
		}
		Entreprise entreprise = entrepriseControl.getEntrepriseById(1);
		for (Departement dep : entreprise.getDepartements()) {
			l.info(dep.getName());
		}
		employeControl.mettreAjourEmailByEmployeIdJPQL("a@gmail.com", 1);
		// employeControl.deleteAllContratJPQL();
		l.info(employeControl.getSalaireByEmployeIdJPQL(1));
		l.info(employeControl.getSalaireMoyenByDepartementId(1));
	}
}
