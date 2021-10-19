package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

@RestController
public class RestControlEmploye {

	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimesheetService itimesheetservice;
	
	private static final Logger logger = LogManager.getLogger(RestControlEmploye.class.getName());
	


	
	// http://localhost:8081/SpringMVC/servlet/ajouterEmployer
	//{"id":1,"nom":"kallel", "prenom":"khaled", "email":"Khaled.kallel@ssiiconsulting.tn", "isActif":true, "role":"INGENIEUR"}
	
	@PostMapping("/ajouterEmployer")
	@ResponseBody
	public Employe ajouterEmploye(@RequestBody Employe employe)
	{ 
		logger.info("dans ajouterEmploye() methode");
		logger.debug("Request{}",employe);	
		iemployeservice.addOrUpdateEmploye(employe);
		logger.trace("A TRACE MESSAGE");
        logger.warn("A WARN Message");    
	    logger.debug("Response{}",employe);
		logger.error("An ERROR Message");
		return employe;
	}
	
	// Modifier email : http://localhost:8081/SpringMVC/servlet/modifyEmail/1/newemail
	@PutMapping(value = "/modifyEmail/{id}/{newemail}") 
	@ResponseBody
	public void mettreAjourEmailByEmployeId(@PathVariable("newemail") String email, @PathVariable("id") int employeId) {
		logger.info("In mettreAjourEmailByEmployeId() method");
		logger.debug("Request{}",email);
		logger.debug("Request{}",employeId);
		iemployeservice.mettreAjourEmailByEmployeId(email, employeId);
		
	}
	// http://localhost:8081/SpringMVC/servlet/affecterEmployeADepartement/1/1
	@PutMapping(value = "/affecterEmployeADepartement/{idemp}/{iddept}") 
	public void affecterEmployeADepartement(@PathVariable("idemp")int employeId, @PathVariable("iddept")int depId) {
		logger.info("affecterEmployeADepartement() method");
		logger.debug("Request{}",depId);
		logger.debug("Request{}",employeId);
		iemployeservice.affecterEmployeADepartement(employeId, depId);
		
	}
	
	// http://localhost:8081/SpringMVC/servlet/desaffecterEmployeDuDepartement/1/1
	@PutMapping(value = "/desaffecterEmployeDuDepartement/{idemp}/{iddept}") 
	public void desaffecterEmployeDuDepartement(@PathVariable("idemp")int employeId, @PathVariable("iddept")int depId)
	{
		logger.info(" In desaffecterEmployeDuDepartement() method");
		logger.debug("Request{}",depId);
		logger.debug("Request{}",employeId);
		iemployeservice.desaffecterEmployeDuDepartement(employeId, depId);
	}

	// http://localhost:8081/SpringMVC/servlet/ajouterContrat
	//{"reference":6,"dateDebut":"2020-03-01","salaire":2000,"typeContrat":"CDD"}
	@PostMapping("/ajouterContrat")
	@ResponseBody
	public int ajouterContrat(@RequestBody Contrat contrat) {
		logger.info("ajouterContrat() method");
		logger.debug("Request{}",contrat);
		iemployeservice.ajouterContrat(contrat);
		return contrat.getReference();
	}
	
	// http://localhost:8081/SpringMVC/servlet/affecterContratAEmploye/6/1
   @PutMapping(value = "/affecterContratAEmploye/{idcontrat}/{idemp}") 
	public void affecterContratAEmploye(@PathVariable("idcontrat")int contratId, @PathVariable("idemp")int employeId)
	{
	    logger.info("affecterContratAEmploye() method");
	    logger.debug("Request{}",contratId);
		logger.debug("Request{}",employeId);
		iemployeservice.affecterContratAEmploye(contratId, employeId);
	}

   // URL : http://localhost:8081/SpringMVC/servlet/getEmployeEmailById/2
   @GetMapping(value = "getEmployeEmailById/{idemp}")
   @ResponseBody
   public String getEmployeEmailById(@PathVariable("idemp")int employeId) {
	    logger.info("getEmployeEmailById() method");
	    logger.debug("Request{}",employeId);
		return iemployeservice.getEmployeEmailById(employeId);
	}
   
   // URL : http://localhost:8081/SpringMVC/servlet/getEmployePrenomById/2
   @GetMapping(value = "getEmployePrenomById/{idemp}")
   @ResponseBody
   public String getEmployePrenomById(@PathVariable("idemp")int employeId) {
	    logger.info("getEmployePrenomById() method");
	    logger.debug("Request{}",employeId);
		return iemployeservice.getEmployePrenomById(employeId);
	}

    // URL : http://localhost:8081/SpringMVC/servlet/deleteEmployeById/1
    @DeleteMapping("/deleteEmployeById/{idemp}") 
	@ResponseBody 
	public void deleteEmployeById(@PathVariable("idemp")int employeId) {
    	logger.info("deleteEmployeById() method");
 	    logger.debug("Request{}",employeId);
		iemployeservice.deleteEmployeById(employeId);
		
	}
    
 // URL : http://localhost:8081/SpringMVC/servlet/deleteContratById/2
    @DeleteMapping("/deleteContratById/{idcontrat}") 
	@ResponseBody
	public void deleteContratById(@PathVariable("idcontrat")int contratId) {
    	logger.info("deleteContratById() method");
 	    logger.debug("Request{}",contratId);
		iemployeservice.deleteContratById(contratId);
	}

    
    // URL : http://localhost:8081/SpringMVC/servlet/getNombreEmployeJPQL
    @GetMapping(value = "getNombreEmployeJPQL")
    @ResponseBody
	public int getNombreEmployeJPQL() {
    	logger.info("getNombreEmployeJPQL() method");
		return iemployeservice.getNombreEmployeJPQL();
	}

    // URL : http://localhost:8081/SpringMVC/servlet/getAllEmployeNamesJPQL
    @GetMapping(value = "getAllEmployeNamesJPQL")
    @ResponseBody
	public List<String> getAllEmployeNamesJPQL() {
    	logger.info("getAllEmployeNamesJPQL() method");
		return iemployeservice.getAllEmployeNamesJPQL();
	}

    // URL : http://localhost:8081/SpringMVC/servlet/getAllEmployeByEntreprise/1
    @GetMapping(value = "getAllEmployeByEntreprise/{identreprise}")
    @ResponseBody
	public List<Employe> getAllEmployeByEntreprise(@PathVariable("identreprise") int identreprise) {
    	logger.info("getAllEmployeByEntreprise() method");
 	    logger.debug("Request{}",identreprise);
    	Entreprise entreprise=ientrepriseservice.getEntrepriseById(identreprise);
		return iemployeservice.getAllEmployeByEntreprise(entreprise);
	}

 // Modifier email : http://localhost:8081/SpringMVC/servlet/mettreAjourEmailByEmployeIdJPQL/2/newemail
 	@PutMapping(value = "/mettreAjourEmailByEmployeIdJPQL/{id}/{newemail}") 
 	@ResponseBody
	public void mettreAjourEmailByEmployeIdJPQL(@PathVariable("newemail") String email, @PathVariable("id") int employeId) {	
 		logger.info("mettreAjourEmailByEmployeIdJPQL() method");
 	    logger.debug("Request{}",email);
 	    logger.debug("Request{}",employeId);
 		iemployeservice.mettreAjourEmailByEmployeIdJPQL(email, employeId);
		
	}

    // URL : http://localhost:8081/SpringMVC/servlet/deleteAllContratJPQL
    @DeleteMapping("/deleteAllContratJPQL") 
	@ResponseBody
	public void deleteAllContratJPQL() {
    	logger.info("deleteAllContratJPQL() method");
		iemployeservice.deleteAllContratJPQL();
		
	}

    // URL : http://localhost:8081/SpringMVC/servlet/getSalaireByEmployeIdJPQL/2
    @GetMapping(value = "getSalaireByEmployeIdJPQL/{idemp}")
    @ResponseBody
	public float getSalaireByEmployeIdJPQL(@PathVariable("idemp")int employeId) {
    	logger.info("getSalaireByEmployeIdJPQL() method");
 	    logger.debug("Request{}",employeId);
		return iemployeservice.getSalaireByEmployeIdJPQL(employeId);
	}

    // URL : http://localhost:8081/SpringMVC/servlet/getSalaireMoyenByDepartementId/2
    @GetMapping(value = "getSalaireMoyenByDepartementId/{iddept}")
    @ResponseBody
	public Double getSalaireMoyenByDepartementId(@PathVariable("iddept")int departementId) {
    	logger.info("getSalaireMoyenByDepartementId() method");
 	    logger.debug("Request{}",departementId);
    	return iemployeservice.getSalaireMoyenByDepartementId(departementId);
	}

	
	//TODO
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		logger.info("getTimesheetsByMissionAndDate() method");
		return iemployeservice.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}


	 // URL : http://localhost:8081/SpringMVC/servlet/getAllEmployes
	@GetMapping(value = "/getAllEmployes")
    @ResponseBody
	public List<Employe> getAllEmployes() {
		logger.info("getAllEmployes() method");
		return iemployeservice.getAllEmployes();
	}

	
	
}
