package tn.esprit.spring;
import org.junit.Assert;
import javax.transaction.Transactional;
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
	
	  Entreprise ssiiConsulting = new Entreprise("SSII Consulting","Cite El Ghazela"); 
	  Departement depTelecom = new Departement("Telecom");
	  Departement depRH = new Departement("RH");
	  Employe employe =new Employe("arij", "mansour", "arijmansour@email.com","123ingenieur", true, Role.INGENIEUR);
		Employe admin =new Employe("hamma", "elhami", "hammaelhami@email.com","123admin", true, Role.ADMINISTRATEUR);
	
	
	@Transactional
    @Test
    void testajouterEmploye(){
    	l.info("you are in testajouterEmployer()");		
		int employeId =employeControl.ajouterEmploye(employe);
		int adminId=employeControl.ajouterEmploye(admin);
        Assert.assertNotNull(employeControl.getEmployePrenomById(employeId));
        Assert.assertNotNull(employeControl.getEmployePrenomById(adminId));
        l.debug( "employe ajouté"+ employeId);
    }
	
	@Transactional
    @Test
    void testaffecterEmployeADepartement(){
		l.info("you are in testaffecterEmployeADepartement()");		
		int employeId =employeControl.ajouterEmploye(employe);
	    ssiiConsulting.addDepartement(depTelecom); 
	    Assert.assertNotNull(employeControl.getEmployePrenomById(employeId));    
		 int depTelecomId = entrepriseControl.ajouterDepartement(depTelecom);
	     employeControl.affecterEmployeADepartement(employeId, depTelecomId);
	 l.debug( "employe "+employeId + "affecter"+" a departement"+ depTelecomId);
    }
	
	@Transactional
    @Test
    void testdesaffecterEmployeADepartement(){
		
		int employeId=employeControl.ajouterEmploye(employe);
	    ssiiConsulting.addDepartement(depTelecom); 
	    Assert.assertNotNull(employeControl.getEmployePrenomById(employeId));    
		int depTelecomId = entrepriseControl.ajouterDepartement(depTelecom);
		 employeControl.affecterEmployeADepartement(employeId, depTelecomId);
	     employeControl.desaffecterEmployeDuDepartement(employeId, depTelecomId);
		l.debug( "employe " + employeId + "desaffecter" + "a departement"+ depTelecomId);   
		}
	
	@Transactional
    @Test
    void testmettreAjourEmailByEmployeId(){	
    Assert.assertNotNull(employeControl.getEmployePrenomById(1));  
	employeControl.mettreAjourEmailByEmployeId("sara@gmail.com",1);
	l.debug("l'email de " +  employeControl.getEmployePrenomById(1) + "est modifier" );

	}
	
	
	@Transactional
    @Test
    void testgetAllDepartements(){	
    List<Departement> listAlldepartements= entrepriseControl.getAllDepartements();
    Assert.assertNotNull(entrepriseControl.getAllDepartements());
	    for(Departement departement : listAlldepartements) { 
	    l.debug("le departement id  :" + departement.getId() +"et le nom: " + departement.getName());}
	    }
	@Transactional
    @Test
    void testgetAllContrats(){	
   
   List<Contrat> listAllContrats =entrepriseControl.getAllContrats();
   Assert.assertNotNull(entrepriseControl.getAllDepartements());
	    for(Contrat contrat : listAllContrats) {
	    	l.debug(contrat.getTypeContrat() + "" +contrat.getReference()+ ""+contrat.getDateDebut()+""+contrat.getSalaire()+""+contrat.getTelephone());
	    	}
	}
	
	/*@Test
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
		 
		
	
   	
  		
	  

       
	}*/
}
