package services;

import domain.*;
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
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mruwzum on 21/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class TeacherServiceTest extends AbstractTest {
   @Autowired
   private TeacherService teacherService;
   @Autowired
   private ActorService actorService;
   @Autowired
   private CreditCardService creditCardService;
   @Autowired
   private UserService userService;
   @Autowired
   private WebinarService webinarService;
   @Autowired
   private CurriculaService curriculaService;
   @Autowired
   private CommentService commentService;


   @Before
   public void setUp() {
   }

   //? B2: An actor who is authenticated as user or teacher must be able to: Edit her or his credit card.

   //Teacher edit creditCard positive&negative Test cases
   @Test
   public void teacherEditCreditCardOk() {
      authenticate("teacher1");
      Teacher teacher = teacherService.findByPrincipal();
      CreditCard creditCard = teacher.getCreditCard();
      Assert.notNull(creditCard);
      int year = creditCard.getYear();
      int newYear = 2050;
      creditCard.setYear(newYear);
      creditCardService.save(creditCard);
      org.junit.Assert.assertNotEquals(year, newYear);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherEditCreditCardNotOk() {
      authenticate(null);
      Teacher teacher = teacherService.findByPrincipal();
      CreditCard creditCard = teacher.getCreditCard();
      Assert.notNull(creditCard);
      int year = creditCard.getYear();
      int newYear = 2050;
      creditCard.setYear(newYear);
      creditCardService.save(creditCard);
      org.junit.Assert.assertNotEquals(year, newYear);
      unauthenticate();
   }

   //User edit creditCard positive&negative test cases
   @Test
   public void userEditCreditCardOk() {
      authenticate("user1");
      User user = userService.findByPrincipal();
      CreditCard creditCard = user.getCreditCard();
      Assert.notNull(creditCard);
      int year = creditCard.getYear();
      int newYear = 2050;
      creditCard.setYear(newYear);
      creditCardService.save(creditCard);
      org.junit.Assert.assertNotEquals(year, newYear);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void userEditCreditCardNotOk() {
      authenticate(null);
      User user = userService.findByPrincipal();
      CreditCard creditCard = user.getCreditCard();
      Assert.notNull(creditCard);
      int year = creditCard.getYear();
      int newYear = 2050;
      creditCard.setYear(newYear);
      creditCardService.save(creditCard);
      org.junit.Assert.assertNotEquals(year, newYear);
      unauthenticate();
   }

   //? B2: An actor who is authenticated as user or teacher must be able to: Make a comment
   //on a webinar, edit a comment or delete it.
//Teacher create comment positive&negative Test cases
   @Test
   public void teacherMakeComment() {
      authenticate("teacher1");
      Teacher teacher = teacherService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(teacher.getWebinars());
      Assert.notNull(webinars);
      Comment comment = commentService.create();
      comment.setTitle("title");
      comment.setText("text");
      comment.setWebinar(webinars.get(0));
      comment.setCreationDate(new Date(System.currentTimeMillis() - 100));
      comment.setOwner(actorService.findByPrincipal());
      commentService.save(comment);
      Assert.notNull(comment);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherMakeCommentNotOk() {
      authenticate(null);
      Teacher teacher = teacherService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(teacher.getWebinars());
      Assert.notNull(webinars);
      Comment comment = commentService.create();
      comment.setTitle("title");
      comment.setText("text");
      comment.setWebinar(webinars.get(0));
      comment.setCreationDate(new Date(System.currentTimeMillis() - 100));
      comment.setOwner(actorService.findByPrincipal());
      commentService.save(comment);
      Assert.notNull(comment);
      unauthenticate();
   }

   //Teacher edit comment positive&negative Test cases
   @Test
   public void teacherEditComment() {
      authenticate("teacher1");
      Teacher teacher = teacherService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(teacher.getWebinars());
      Assert.notNull(webinars);
      List<Comment> comments = new ArrayList<>(webinars.get(0).getComments());
      String title = comments.get(0).getTitle();
      String newTitle = "newTi";
      comments.get(0).setTitle(newTitle);
      org.junit.Assert.assertNotEquals(title, comments.get(0).getTitle());
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherEditCommentNotOk() {
      authenticate(null);
      Teacher teacher = teacherService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(teacher.getWebinars());
      Assert.notNull(webinars);
      List<Comment> comments = new ArrayList<>(webinars.get(0).getComments());
      String title = comments.get(0).getTitle();
      String newTitle = "newTi";
      comments.get(0).setTitle(newTitle);
      org.junit.Assert.assertNotEquals(title, comments.get(0).getTitle());
      unauthenticate();
   }

   //Teacher delete comment positive&negative Test cases
   @Test
   public void teacherDeleteComment() {
      authenticate("teacher1");
      Teacher teacher = teacherService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(teacher.getWebinars());
      Assert.notNull(webinars);
      List<Comment> comments = new ArrayList<>(webinars.get(0).getComments());
      commentService.delete(comments.get(0));
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherDeleteCommentNotOk() {
      authenticate(null);
      Teacher teacher = teacherService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(teacher.getWebinars());
      Assert.notNull(webinars);
      List<Comment> comments = new ArrayList<>(webinars.get(0).getComments());
      commentService.delete(comments.get(0));
      unauthenticate();
   }

   //User create comment positive&negative test cases
   @Test
   public void userMakeCommentOk() {
      authenticate("user1");
      User user = userService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(webinarService.findAll());
      Assert.notNull(webinars);
      Comment comment = commentService.create();
      comment.setTitle("title");
      comment.setText("text");
      comment.setWebinar(webinars.get(0));
      comment.setCreationDate(new Date(System.currentTimeMillis() - 100));
      comment.setOwner(user);
      commentService.save(comment);
      Assert.notNull(comment);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void userMakeCommentNotOk() {
      authenticate(null);
      User user = userService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(webinarService.findAll());
      Assert.notNull(webinars);
      Comment comment = commentService.create();
      comment.setTitle("title");
      comment.setText("text");
      comment.setWebinar(webinars.get(0));
      comment.setCreationDate(new Date(System.currentTimeMillis() - 100));
      comment.setOwner(user);
      commentService.save(comment);
      Assert.notNull(comment);
      unauthenticate();
   }

   //User edit comment positive&negative Test cases
   @Test
   public void userEditCommentOk() {
      authenticate("user1");
      User user = userService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(webinarService.findAll());
      Assert.notNull(webinars);
      List<Comment> comments = new ArrayList<>(webinars.get(0).getComments());
      String title = comments.get(0).getTitle();
      String newTitle = "newTi";
      comments.get(0).setTitle(newTitle);
      org.junit.Assert.assertNotEquals(title, comments.get(0).getTitle());
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void userEditCommentNotOk() {
      authenticate(null);
      User user = userService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(webinarService.findAll());
      Assert.notNull(webinars);
      List<Comment> comments = new ArrayList<>(webinars.get(0).getComments());
      String title = comments.get(0).getTitle();
      String newTitle = "newTi";
      comments.get(0).setTitle(newTitle);
      org.junit.Assert.assertNotEquals(title, comments.get(0).getTitle());
      unauthenticate();
   }

   //User delete comment positive&negative Test cases
   @Test
   public void userDeleteComment() {
      authenticate("user1");
      User user = userService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(webinarService.findAll());
      Assert.notNull(webinars);
      List<Comment> comments = new ArrayList<>(webinars.get(0).getComments());
      commentService.delete(comments.get(0));
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void userDeleteCommentNotOk() {
      authenticate(null);
      User user = userService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(webinarService.findAll());
      Assert.notNull(webinars);
      List<Comment> comments = new ArrayList<>(webinars.get(0).getComments());
      commentService.delete(comments.get(0));
      unauthenticate();
   }
   @Test
   public void totalEarn() throws Exception {
   }

   @Test
   public void findByUserAccount() throws Exception {
   }

   @Test
   public void myBills() throws Exception {
   }

}