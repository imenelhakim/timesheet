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
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@SpringBootTest
class TimesheetApplicationTests {
	private static final Logger l = LogManager.getLogger(TimesheetApplicationTests.class.getName());
	@Autowired
	ControllerEntrepriseImpl entrepriseControl;
	@Autowired
	ControllerEmployeImpl employeControl;
	@Autowired
	RestControlEmploye RestemployeControl;
	@Autowired
	EntrepriseRepository entrepriseRepo;
	@Autowired
	EmployeRepository employeRepo;
	@Autowired 
	DepartementRepository deprepo;
	
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
        Assert.assertNotNull(employeRepo.findById(employeId));
        Assert.assertNotNull(employeRepo.findById(adminId));
        l.debug( "employe ajouté"+ employeId);
    }
	
	@Transactional
    @Test
    void testaffecterEmployeADepartement(){
		l.info("you are in testaffecterEmployeADepartement()");		
		int employeId =employeControl.ajouterEmploye(employe);
	    ssiiConsulting.addDepartement(depTelecom); 
	    Assert.assertNotNull(employeRepo.findById(employeId));
		 int depTelecomId = entrepriseControl.ajouterDepartement(depTelecom);
	     employeControl.affecterEmployeADepartement(employeId, depTelecomId);
	 l.debug( "employe "+employeId + "affecter"+" a departement"+ depTelecomId);
    }
	
	@Transactional
    @Test
    void testdesaffecterEmployeADepartement(){
		l.info("you are in testdesaffecterEmployeADepartement()");		
		int employeId=employeControl.ajouterEmploye(employe);
	    ssiiConsulting.addDepartement(depTelecom); 
	    Assert.assertNotNull(employeRepo.findById(employeId));
		int depTelecomId = entrepriseControl.ajouterDepartement(depTelecom);
	    Assert.assertNotNull(deprepo.findById(depTelecomId));
		 employeControl.affecterEmployeADepartement(employeId, depTelecomId);
	     employeControl.desaffecterEmployeDuDepartement(employeId, depTelecomId);
		l.debug( "employe " + employeId + "desaffecter" + "a departement"+ depTelecomId);   
		}
	
	@Transactional
    @Test
    void testmettreAjourEmailByEmployeId(){	
		l.info("you are in testmettreAjourEmailByEmployeId()");		
    Assert.assertNotNull(employeControl.getEmployePrenomById(1));  
	employeControl.mettreAjourEmailByEmployeId("sara@gmail.com",1);
	l.debug("l'email de " +  employeControl.getEmployePrenomById(1) + "est modifier" );

	}
	
	
	@Transactional
    @Test
    void testgetAllDepartements(){	
		l.info("you are in testgetAllDepartements()");		
    List<Departement> listAlldepartements= entrepriseControl.getAllDepartements();
    Assert.assertNotNull(entrepriseControl.getAllDepartements());
	    for(Departement departement : listAlldepartements) { 
	    l.debug("le departement id  :" + departement.getId() +"et le nom: " + departement.getName());}
	    }
	@Transactional
    @Test
    void testgetAllContrats(){
		l.info("you are in testgetAllContrats()");		
   List<Contrat> listAllContrats =entrepriseControl.getAllContrats();
   Assert.assertNotNull(entrepriseControl.getAllDepartements());
	    for(Contrat contrat : listAllContrats) {
	    	l.debug(contrat.getTypeContrat() + "" +contrat.getReference()+ ""+contrat.getDateDebut()+""+contrat.getSalaire()+""+contrat.getTelephone());
	    	}
	}
	
	@Transactional
    @Test
	void testgetEntrepriseById() {
		l.info("you are in testgetEntrepriseById()");		
		   Entreprise entreprise= new Entreprise("telecom","telelcom");
	        int identreprise=entrepriseControl.ajouterEntreprise((entreprise));
	        Assert.assertNotNull( entrepriseRepo.findById(identreprise).get());
	        
	        Assert.assertNotNull(entrepriseControl.getEntrepriseById(identreprise));
	        
		l.debug("l'entreprise :" + entrepriseRepo.findById(identreprise).get()  );
		
	}
	
}
