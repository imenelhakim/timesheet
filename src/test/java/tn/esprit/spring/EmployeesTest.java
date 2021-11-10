package tn.esprit.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.controller.ControllerEmployeImpl;
import tn.esprit.spring.controller.ControllerEntrepriseImpl;
import tn.esprit.spring.controller.ControllerTimesheetImpl;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.DepartementServiceImpl;
import tn.esprit.spring.services.EmployeServiceImpl;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class EmployeesTest {
    private static final Logger l = LogManager.getLogger(TimesheetApplication.class);

    @Autowired
    ControllerEntrepriseImpl enterpriseControl;
    @Autowired
    DepartementServiceImpl departmentService;
    @Autowired
    ControllerEmployeImpl employeeControl;
    @Autowired
    EmployeServiceImpl employeeService;
    @Autowired
    ControllerTimesheetImpl timesheetControl;
    @Autowired
    EmployeRepository employeeRepo;

    @Transactional
    @Test
    void getAllEmployeesTest(){
        Employe employe =new Employe("feki", "najd", "fekinajd@email.com","123ingenieur", true, Role.INGENIEUR);
        Employe admin =new Employe("feki2", "najd2", "fekinajd2@email.com","123admin", true, Role.ADMINISTRATEUR);
        l.info("Get All Employees Test Started");
        employeeControl.ajouterEmploye(employe);
        employeeControl.ajouterEmploye(admin);
        List<Employe> employees =employeeControl.getAllEmployes();
        Assertions.assertNotNull(employees);
        l.info("Get All Employees Test Finished");
        l.debug( "Employees list"+ employees);
    }
    @Transactional
    @Test
    void addMissionTest(){
        Mission missionNajd = new Mission("missionNajd","mission de travail à sfax");
        l.info("Add Mission Test Started");
        int idMission = timesheetControl.ajouterMission(missionNajd);
        Assertions.assertNotNull(idMission);
        l.info("Add Mission Test Finished");
        l.debug( "The Added Mission'Id "+ idMission );
    }
    @Transactional
    @Test
    void addTimesheet(){
        Mission missionNajd = new Mission("missionNajd","mission de travail à sfax");
        Employe admin =new Employe("feki2", "najd2", "fekinajd2@email.com","123admin", true, Role.ADMINISTRATEUR);
        l.info("Add New Timesheet Test Started");
        int employeeId = employeeControl.ajouterEmploye(admin);
        int missionId = timesheetControl.ajouterMission(missionNajd);
        Timesheet timesheetNajd = timesheetControl.ajouterTimesheet(missionId,employeeId,new Date(1 / 2020),new Date(1 / 2021));
        Assertions.assertNotNull(timesheetNajd);
        l.info("Add New Timesheet Test Finished");
        l.debug( "The Added Timesheet "+ timesheetNajd );
    }
    @Transactional
    @Test
    void getTimesheetsByMissionAndDateTest(){
        Employe employe =new Employe("feki", "najd", "fekinajd@email.com","123ingenieur", true, Role.INGENIEUR);
        Employe admin =new Employe("feki2", "najd2", "fekinajd2@email.com","123admin", true, Role.ADMINISTRATEUR);
        l.info("Get Timesheet Test Started");
        employeeControl.ajouterEmploye(employe);
        employeeControl.ajouterEmploye(admin);
        Mission missionNajd = new Mission("missionNajd","mission de travail à sfax");
        Mission missionFeki = new Mission("missioneki","mission de travail à sfax");
        timesheetControl.ajouterMission(missionNajd);
        timesheetControl.ajouterMission(missionFeki);
        List<Timesheet> timesheetNajd = employeeControl.getTimesheetsByMissionAndDate(employe , missionNajd , new Date(1 / 2019) , new Date(1 / 2021));
        List<Timesheet> timesheetFeki = employeeControl.getTimesheetsByMissionAndDate(admin , missionFeki , new Date(1 / 2019) , new Date(1 / 2021));
        Assertions.assertNotNull(timesheetNajd);
        Assertions.assertNotNull(timesheetFeki);
        l.info("Get Timesheet Test Finished");
        l.debug( "Timesheet list"+ timesheetNajd + timesheetFeki );
    }

    @Transactional
    @Test
    void addOrUpdateEmploye(){
        Employe employe =new Employe("feki", "najd", "fekinajd@email.com","123ingenieur", true, Role.INGENIEUR);
        Employe admin =new Employe("feki2", "najd2", "fekinajd2@email.com","123admin", true, Role.ADMINISTRATEUR);
        l.info("Add or Update Test Started");
        employeeService.addOrUpdateEmploye(employe);
        employeeService.addOrUpdateEmploye(admin);
        admin.setPassword("newPassword");
        employe.setPassword("newPassword2");
        employeeService.addOrUpdateEmploye(employe);
        employeeService.addOrUpdateEmploye(admin);
        List<Employe> employees =employeeControl.getAllEmployes();
        Assertions.assertNotNull(employees);
        l.info("Add Or Update  Test Finished");
        l.debug( "Add Or Update test results"+ employees );
    }

}