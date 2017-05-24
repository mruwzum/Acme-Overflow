package services;

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

import java.util.Collection;
import java.util.HashSet;

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

   @Test
   public void register() {
   }

   @Test
   public void unregister() {
   }

   @Test
   public void myWebinars() {
   }

   @Test
   public void myWebinarsO() {
   }

   @Test
   public void webinarsToGo() {
   }

}