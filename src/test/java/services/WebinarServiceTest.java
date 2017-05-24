package services;

import domain.Bill;
import domain.User;
import domain.Webinar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import utilities.AbstractTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mruwzum on 21/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class WebinarServiceTest extends AbstractTest {
   @Autowired
   private WebinarService webinarService;
   @Autowired
   private ActorService actorService;
   @Autowired
   private QuestionService questionService;
   @Autowired
   private UserService userService;
   @Autowired
   private TeacherService teacherService;
   @Autowired
   private DutyService dutyService;
   @Autowired
   private BillService billService;


   @Before
   public void setUp() {
   }


   //An actor who is not authenticathed must be able to breowse the list of incoming webinars grouped by their categories
   @Test
   public void listOfIncomingWebinsrsOK() {
      Collection<Webinar> webinars = webinarService.listIncoming();
      Assert.notEmpty(webinars);
   }

   @Test(expected = IllegalArgumentException.class)
   public void listOfIncomingwebinarsNotOk() {
      Collection<Webinar> webinars = new HashSet<>();
      Assert.notEmpty(webinars);
   }

//an actor who is not authenticathed must be able to browse the list of all webinars organized o the system
   //including the price
   @Test
   public void listOfAllWebinsrsOK() {
      Collection<Webinar> webinars = webinarService.findAll();
      Assert.notEmpty(webinars);
   }

   @Test(expected = IllegalArgumentException.class)
   public void listOfAllWebinarsNotOk() {
      Collection<Webinar> webinars = new HashSet<>();
      Assert.notEmpty(webinars);
   }

   //An actor authenticated as user must be able to register to a webinar
   @Test
   public void registerToAWebinarOk() {
      authenticate("user1");
      List<Webinar> webinars = new ArrayList<>(webinarService.findAll());
      Webinar webinar = webinars.get(0);
      boolean register = webinarService.register(userService.findByPrincipal(), webinar);
      Assert.isTrue(register);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void registerToAWebinarNotOk() {
      authenticate(null);
      List<Webinar> webinars = new ArrayList<>(webinarService.findAll());
      Webinar webinar = webinars.get(0);
      boolean register = webinarService.register(userService.findByPrincipal(), webinar);
      Assert.isTrue(register);
      unauthenticate();
   }

   //An actor authenticated as user must be able to view a record of his/her payments
   @Test
   public void recordPaymentsOk() {
      authenticate("user1");
      List<Webinar> webinars = new ArrayList<>(webinarService.findAll());
      Webinar webinar = webinars.get(0);
      boolean register = webinarService.register(userService.findByPrincipal(), webinar);
      Assert.isTrue(register);
      Collection<Bill> bills = billService.findAll();
      List<Bill> res = new ArrayList<>();
      for (Bill b : bills) {
         if (b.getOwner().equals(userService.findByPrincipal())) {
            res.add(b);
         }
         Assert.notEmpty(res);
      }
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void recordPaymentsNotOk() {
      authenticate(null);
      List<Webinar> webinars = new ArrayList<>(webinarService.findAll());
      Webinar webinar = webinars.get(0);
      boolean register = webinarService.register(userService.findByPrincipal(), webinar);
      Assert.isTrue(register);
      Collection<Bill> bills = billService.findAll();
      List<Bill> res = new ArrayList<>();
      for (Bill b : bills) {
         if (b.getOwner().equals(userService.findByPrincipal())) {
            res.add(b);
         }
         Assert.notEmpty(res);
      }
      unauthenticate();
   }

   // TODO an actor authenticated as a user must be able to make a webinar evaluation once it has finished and the user has assisted to it.


   // An authenticated user must be able to View the webinar in witch his/her is registered.
   @Test
   public void myRegisteredWebinarsOk() {
      authenticate("user1");
      List<Webinar> webinars = new ArrayList<>(webinarService.webinarsToGo(userService.findByPrincipal()));
      Assert.notEmpty(webinars);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void myRegisteredWebinarsNotOk() {
      authenticate(null);
      List<Webinar> webinars = new ArrayList<>(webinarService.webinarsToGo(userService.findByPrincipal()));
      Assert.notEmpty(webinars);
      unauthenticate();
   }

   //An user registered as a teacher must be able to watch his/her webinars and edit them

   @Test
   public void myWebinarsOk() {
      authenticate("teacher1");
      List<Webinar> webinars = new ArrayList<>(webinarService.myWebinars(teacherService.findByPrincipal()));
      Assert.notEmpty(webinars);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void myWebinarsnotOk() {
      authenticate(null);
      List<Webinar> webinars = new ArrayList<>(webinarService.myWebinars(teacherService.findByPrincipal()));
      Assert.notEmpty(webinars);
      unauthenticate();
   }

   @Test
   public void editMyWebinarsOk() {
      authenticate("teacher1");
      List<Webinar> webinars = new ArrayList<>(webinarService.myWebinars(teacherService.findByPrincipal()));
      Assert.notEmpty(webinars);
      Webinar webinar = webinars.get(0);
      String prevDescr = webinar.getDescription();
      String newDescr = "fglksdfjgsdñlfgkjdñfg";
      webinar.setDescription(newDescr);
      org.junit.Assert.assertNotEquals(prevDescr, webinar.getDescription());
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void editMyWebinarsnotOk() {
      authenticate(null);
      List<Webinar> webinars = new ArrayList<>(webinarService.myWebinars(teacherService.findByPrincipal()));
      Assert.notEmpty(webinars);
      Webinar webinar = webinars.get(0);
      String prevDescr = webinar.getDescription();
      String newDescr = "fglksdfjgsdñlfgkjdñfg";
      webinar.setDescription(newDescr);
      org.junit.Assert.assertNotEquals(prevDescr, webinar.getDescription());
      unauthenticate();
   }


}