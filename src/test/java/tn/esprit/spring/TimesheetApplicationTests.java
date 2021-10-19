package tn.esprit.spring;

import static org.junit.Assert.assertNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.controller.ControllerEmployeImpl;
import tn.esprit.spring.entities.Employe;



@SpringBootTest
class TimesheetApplicationTests {
	
	private static final Logger l = LogManager.getLogger(TimesheetApplicationTests.class.getName());
	 Employe employe=new Employe();
	@Autowired
	ControllerEmployeImpl employeControl;
   @Test
	void contextLoads() {
	   employeControl.mettreAjourEmailByEmployeId("sara@gmail.com",11);
	   /*String val = employe.;
      
        assertNull("Failed to return null for non existing employee", val);*/
	    l.info("update employe email in test function ");
	    
   }

	
}
