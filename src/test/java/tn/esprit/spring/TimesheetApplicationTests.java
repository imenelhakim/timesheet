package tn.esprit.spring;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.controller.ControllerEmployeImpl;
import tn.esprit.spring.controller.ControllerEntrepriseImpl;
import tn.esprit.spring.controller.RestControlEmploye;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Role;

@SpringBootTest
class TimesheetApplicationTests {
	private static final Logger l = LogManager.getLogger(TimesheetApplicationTests.class.getName());
	@Autowired
	ControllerEntrepriseImpl entrepriseControl;
	@Autowired
	ControllerEmployeImpl employeControl;
	@Autowired
	RestControlEmploye RestemployeControl;
	
	@Test
	void contextLoads() {

		
		  Entreprise ssiiConsulting = new Entreprise("SSII Consulting","Cite El Ghazela"); 
		  Departement depTelecom = new Departement("Telecom");
		  Departement depRH = new Departement("RH");
		  ssiiConsulting.addDepartement(depRH);
		  ssiiConsulting.addDepartement(depTelecom); 
		  int ssiiConsultingId =entrepriseControl.ajouterEntreprise(ssiiConsulting);
		  ssiiConsulting.setId(ssiiConsultingId);
		  depTelecom.setEntreprise(ssiiConsulting); 
		  int depTelecomId = entrepriseControl.ajouterDepartement(depTelecom);
		  depRH.setEntreprise(ssiiConsulting);
		  int depRhId =entrepriseControl.ajouterDepartement(depRH);
		  
		  entrepriseControl.affecterDepartementAEntreprise(depTelecomId,ssiiConsultingId); 
		  entrepriseControl.affecterDepartementAEntreprise(depRhId, ssiiConsultingId);
		  List<String> departements = entrepriseControl.getAllDepartementsNamesByEntreprise(ssiiConsultingId); 
		  for(String departementName : departements) { l.info(departementName); }
		  Entreprise entreprise = entrepriseControl.getEntrepriseById(1); 
		  for (Departement dep : entreprise.getDepartements()) { l.info(dep.getName()); }
		  employeControl.mettreAjourEmailByEmployeIdJPQL("a@gmail.com", 1); 
		 // employeControl.deleteAllContratJPQL();
		  l.info(employeControl.getSalaireByEmployeIdJPQL(1));
		  l.info(employeControl.getSalaireMoyenByDepartementId(1));
		 //////***********************************************************************************************naj
		 Employe employe =new Employe("arij", "mansour", "arijmansour@email.com","123ingenieur", true, Role.INGENIEUR);
		 Employe admin =new Employe("hamma", "elhami", "hammaelhami@email.com","123admin", true, Role.ADMINISTRATEUR);
		 int employeId =employeControl.ajouterEmploye(employe);
		employeControl.ajouterEmploye(admin);
		 l.info( "employe ajouté"+ employeId);
	/*f1*/	employeControl.affecterEmployeADepartement(employeId, depTelecomId);
		 l.info( "employe "+employeId + "affecter"+" a departement"+ depTelecomId);
   /*f2*/	employeControl.desaffecterEmployeDuDepartement(employeId, depTelecomId);
		 l.info( "employe " + employeId + "desaffecter" + "a departement"+ depTelecomId);
  /*f3*/		employeControl.mettreAjourEmailByEmployeId("sara@gmail.com",1);
	  
  /*f4*/    List<Departement> listAlldepartements= entrepriseControl.getAllDepartements();
	    for(Departement departement : listAlldepartements) { l.info(departement.getId() +" " + departement.getName());}
 /*f5*/    List<Contrat> listAllContrats =entrepriseControl.getAllContrats();
	    for(Contrat contrat : listAllContrats) { l.info(contrat.getTypeContrat() + "" +contrat.getReference()+ ""+contrat.getDateDebut()+""+contrat.getSalaire()+""+contrat.getTelephone());}
          //String email=admin.getEmail();
         // String password=admin.getPassword();
 /*f6*/   //String login= employeControl.doLogin(){iemployeservice.authenticate(email, password);};
		// l.info(login);
	    
	}
}
