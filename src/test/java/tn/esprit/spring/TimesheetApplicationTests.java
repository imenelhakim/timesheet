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
import tn.esprit.spring.controller.ControllerTimesheetImpl;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@SpringBootTest
class TimesheetApplicationTests {
	private static final Logger l = LogManager.getLogger(TimesheetApplicationTests.class.getName());
	@Autowired
	ControllerEntrepriseImpl entrepriseControl;
	@Autowired
	ControllerEmployeImpl employeControl;

	@Autowired
	EntrepriseRepository entrepriseRepo;
	@Autowired
	EmployeRepository employeRepo;
	@Autowired 
	DepartementRepository deprepo;
	@Autowired 
	ControllerTimesheetImpl controllerTimesheet;
	@Autowired
	TimesheetRepository timesheetRepository ;

	Departement depRH = new Departement("RH");



	@Transactional
	@Test
	void testajouterEmploye(){
		Employe employe =new Employe("arij", "mansour", "arijmansour@email.com","123ingenieur", true, Role.INGENIEUR);
		Employe admin =new Employe("hamma", "elhami", "hammaelhami@email.com","123admin", true, Role.ADMINISTRATEUR);
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
		Employe employe =new Employe("arij", "mansour", "arijmansour@email.com","123ingenieur", true, Role.INGENIEUR);
		Departement depTelecom = new Departement("Telecom");
		int employeId =employeControl.ajouterEmploye(employe);

		Entreprise ssiiConsulting = new Entreprise("SSII Consulting","Cite El Ghazela"); 

		ssiiConsulting.addDepartement(depTelecom); 
		Assert.assertNotNull(employeRepo.findById(employeId));   
		int depTelecomId = entrepriseControl.ajouterDepartement(depTelecom);
		Assert.assertNotNull(deprepo.findById(depTelecomId));
		employeControl.affecterEmployeADepartement(employeId, depTelecomId);
		l.debug( "employe "+employeId + "affecter"+" a departement"+ depTelecomId);
	}

	@Transactional
	@Test
	void testdesaffecterEmployeADepartement(){
		l.info("you are in testdesaffecterEmployeADepartement()");	
		Employe employe =new Employe("arij", "mansour", "arijmansour@email.com","123ingenieur", true, Role.INGENIEUR);

		Entreprise ssiiConsulting = new Entreprise("SSII Consulting","Cite El Ghazela"); 

		int employeId=employeControl.ajouterEmploye(employe);
		Departement depTelecom = new Departement("Telecom");
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
		Employe employe =new Employe("mohamed", "mansour", "mohamedmansour@email.com","123ingenieur", true, Role.INGENIEUR);
		int employeId=employeControl.ajouterEmploye(employe);
		Assert.assertNotNull(employeControl.getEmployePrenomById(employeId));  
		employeControl.mettreAjourEmailByEmployeId("ingenieur@gmail.com",employeId);
		l.debug("l'email de " +  employeControl.getEmployePrenomById(employeId) + "est modifier" );

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

	@Transactional
	@Test
	void testajouterMission() {
		l.info("you are in testajouterMission()");	
		Mission mission= new Mission("development web","spring application");			
		controllerTimesheet.ajouterMission(mission);
		l.warn("aucun mission ajouter!!! ");


	}
	@Transactional
	@Test
	void testaffecterMissionADepartement()
	{
		l.info("you are in testaffecterMissionADepartement()");
		Mission mission= new Mission("development web","spring application");
		Departement depTelecom = new Departement("Telecom");
		int depTelecomId = entrepriseControl.ajouterDepartement(depTelecom);
		int missionId =controllerTimesheet.ajouterMission(mission);
		Assert.assertNotNull(deprepo.findById(depTelecomId));
		controllerTimesheet.affecterMissionADepartement(missionId, depTelecomId);
	}


}
