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

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Collection;
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
   @Autowired
   private MezzageService mezzageService;
   @Autowired
   private FolderService folderService;


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

   //COMPLETE TEST CASES FROM A REQUIREMENT :(A1) An actor who is authenticated as user or teacher must be able to exchange messages with other actors of the system
   //Send message positive cases from user to user, user to teacher, teacher to user and teacher to teacher.
   @Test
   public void userToUserSendMessageOk() {
      authenticate("user1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }
   @Test
   public void userToTeacherSendMessageOk() {
      authenticate("user1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }
   @Test
   public void teacherToUserSendMessageOk() {
      authenticate("teacher1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   @Test
   public void teacherToTeacherSendMessageOk() {
      authenticate("teacher1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   //Negative test cases with empty body from user to user, user to teacher, teacher to user and teacher to teacher.
   @Test(expected = IllegalArgumentException.class)
   public void userToUserSendMessageNotOk() {
      authenticate("user1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user1mail@gmail.com");
      mezzage.setPriority(Priority.MEDIUM);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void userToTeacherSendMessageNotOk() {
      authenticate("user1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherToUserSendMessageNotOk() {
      authenticate("teacher1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherToTeacherSendMessageNotOk() {
      authenticate("teacher1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   //Negative test cases with empty senderMail from user to user, user to teacher, teacher to user and teacher to teacher.
   @Test
   public void userToUserSendMessageOkNoSenderMail() {
      authenticate("user1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("body");
      mezzage.setReceiverEmail("user1mail@gmail.com");
      mezzage.setPriority(Priority.MEDIUM);
      actorService.textMessage(mezzage);
      unauthenticate();
   }

   @Test
   public void userToTeacherSendMessageOkNoSenderMail() {
      authenticate("user1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("body");
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      unauthenticate();
   }

   @Test
   public void teacherToUserSendMessageOkNoSenderMail() {
      authenticate("teacher1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("body");
      mezzage.setReceiverEmail("user1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      unauthenticate();
   }

   @Test
   public void teacherToTeacherSendMessageOkNoSenderMail() {
      authenticate("teacher1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("body");
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      unauthenticate();
   }

   //Negative test cases with empty subject from user to user, user to teacher, teacher to user and teacher to teacher.
   @Test(expected = IllegalArgumentException.class)
   public void userToUserSendMessageNotOkSubject() {
      authenticate("user1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void userToTeacherSendMessageNotOkSubject() {
      authenticate("user1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherToUserSendMessageNotOkSubject() {
      authenticate("teacher1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherToTeacherSendMessageNotOkSubject() {
      authenticate("teacher1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   //Negative test cases with empty receiver mail from user to user, user to teacher, teacher to user and teacher to teacher.
   @Test(expected = IllegalArgumentException.class)
   public void userToUserSendMessageNotOkReceiverNull() {
      authenticate("user1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setPriority(Priority.MEDIUM);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void userToTeacherSendMessageNotOkReceiverNull() {
      authenticate("user1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setPriority(Priority.HIGH);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherToUserSendMessageNotOkReceiverNull() {
      authenticate("teacher1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setPriority(Priority.MEDIUM);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherToTeacherSendMessageNotOkReceiverNull() {
      authenticate("teacher1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setPriority(Priority.HIGH);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   //positive test cases with empty priority from user to user, user to teacher, teacher to user and teacher to teacher.
   @Test
   public void userToUserSendMessageOkNoPriority() {
      authenticate("user1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user1mail@gmail.com");
      actorService.textMessage(mezzage);
      Assert.isNull(mezzage.getPriority());
      unauthenticate();
   }

   @Test
   public void userToTeacherSendMessageOkNoPriority() {
      authenticate("user1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      actorService.textMessage(mezzage);
      Assert.isNull(mezzage.getPriority());
      unauthenticate();
   }

   @Test
   public void teacherToUserSendMessageOkNoPriority() {
      authenticate("teacher1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user1mail@gmail.com");
      actorService.textMessage(mezzage);
      Assert.isNull(mezzage.getPriority());
      unauthenticate();
   }

   @Test
   public void teacherToTeacherSendMessageOkNoPriority() {
      authenticate("teacher1");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      actorService.textMessage(mezzage);
      Assert.isNull(mezzage.getPriority());
      unauthenticate();
   }

   //negative test cases with non authenticated user or teacher from user to user, user to teacher, teacher to user and teacher to teacher.
   @Test(expected = IllegalArgumentException.class)
   public void userToUserSendMessageNotOkNoLog() {
      authenticate(null);
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user1mail@gmail.com");
      mezzage.setPriority(Priority.MEDIUM);
      actorService.textMessage(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void userToTeacherSendMessageNotOkNoLog() {
      authenticate(null);
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherToUserSendMessageNotOkNoLog() {
      authenticate(null);
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user1mail@gmail.com");
      actorService.textMessage(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherToTeacherSendMessageNotOkNoLog() {
      authenticate(null);
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      actorService.textMessage(mezzage);
      unauthenticate();
   }
   //(A1) An actor who is authenticated as user or teacher must be able to manage his/her message folders which includes creating, listing, modify and the deletion of them.

   //Listing positive&negative test cases
   @Test
   public void listFoldersOkUser() {
      authenticate("user1");
      User user = userService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      Folder outbox = actorService.folderByName(user, "Outbox");
      Assert.notNull(outbox);
      Folder trashbox = actorService.folderByName(user, "Trashbox");
      Assert.notNull(trashbox);
      Folder sopambox = actorService.folderByName(user, "Spambox");
      Assert.notNull(sopambox);
      unauthenticate();
   }

   @Test
   public void listFoldersOkTeacher() {
      authenticate("teacher1");
      Teacher user = teacherService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      Folder outbox = actorService.folderByName(user, "Outbox");
      Assert.notNull(outbox);
      Folder trashbox = actorService.folderByName(user, "Trashbox");
      Assert.notNull(trashbox);
      Folder sopambox = actorService.folderByName(user, "Spambox");
      Assert.notNull(sopambox);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void listFoldersNotOkUser() {
      authenticate("user1");
      User user = userService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "f");
      Assert.notNull(inbox);
      Folder outbox = actorService.folderByName(user, "p");
      Assert.notNull(outbox);
      Folder trashbox = actorService.folderByName(user, "a");
      Assert.notNull(trashbox);
      Folder sopambox = actorService.folderByName(user, "t");
      Assert.notNull(sopambox);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void listFoldersNotOkTeacher() {
      authenticate("teacher1");
      Teacher user = teacherService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "f");
      Assert.notNull(inbox);
      Folder outbox = actorService.folderByName(user, "p");
      Assert.notNull(outbox);
      Folder trashbox = actorService.folderByName(user, "a");
      Assert.notNull(trashbox);
      Folder sopambox = actorService.folderByName(user, "t");
      Assert.notNull(sopambox);
      unauthenticate();
   }

   //Creating, positive and negative test cases
   @Test
   public void createFolderOkUser() {
      authenticate("user1");
      User user = userService.findByPrincipal();
      Folder folder = folderService.create();
      folder.setName("dfdgsdfgsdfgdfds");
      folder.setOwner(user);
      user.getFolders().add(folder);
      folderService.save(folder);
      unauthenticate();
   }

   @Test
   public void createFolderOkTeacher() {
      authenticate("teacher1");
      Teacher user = teacherService.findByPrincipal();
      Folder folder = folderService.create();
      folder.setName("dfdfgds");
      folder.setOwner(user);
      user.getFolders().add(folder);
      folderService.save(folder);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void createFolderNotOkUser() {
      authenticate("user123");
      User user = userService.findByPrincipal();
      Folder folder = folderService.create();
      folder.setName("");
      folderService.save(folder);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void createFolderNotOkTeacher() {
      authenticate("teacher123");
      Teacher user = teacherService.findByPrincipal();
      Folder folder = folderService.create();
      folder.setName("");
      folderService.save(folder);
      unauthenticate();
   }

   //deleting, positive and negative test cases
   @Test
   public void deleteFolderOkUser() {
      authenticate("user1");
      User user = userService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      folderService.delete(inbox);
      unauthenticate();
   }

   @Test
   public void deleteFolderOkTeacher() {
      authenticate("teacher1");
      Teacher user = teacherService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      folderService.delete(inbox);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void deleteFolderNotOkUser() {
      authenticate("user123");
      User user = userService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      folderService.delete(inbox);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void deleteFolderNotOkTeacher() {
      authenticate("teacher123");
      Teacher user = teacherService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      folderService.delete(inbox);
      unauthenticate();
   }

   //editing, positive and negative test cases
   @Test
   public void editFolderOkUser() {
      authenticate("user1");
      User user = userService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      String name0 = inbox.getName();
      inbox.setName("perri");
      folderService.save(inbox);
      org.junit.Assert.assertNotEquals(name0, inbox.getName());
      unauthenticate();
   }

   @Test
   public void editFolderOkTeacher() {
      authenticate("teacher1");
      Teacher user = teacherService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      String name0 = inbox.getName();
      inbox.setName("perri");
      folderService.save(inbox);
      org.junit.Assert.assertNotEquals(name0, inbox.getName());
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void editFolderNotOkUSer() {
      authenticate("user123");
      User user = userService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      String name0 = inbox.getName();
      inbox.setName("perri");
      folderService.save(inbox);
      org.junit.Assert.assertNotEquals(name0, inbox.getName());
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void editFolderNotOkTeacher() {
      authenticate("teacher123");
      Teacher user = teacherService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      String name0 = inbox.getName();
      inbox.setName("perri");
      folderService.save(inbox);
      org.junit.Assert.assertNotEquals(name0, inbox.getName());
      unauthenticate();
   }

   //(A1) An actor who is authenticated as user or teacher must be able to manage his or her messages, which includes send them, delete them ,move from one folder to another.
   //deleting messages
   @Test
   public void deletingMessageUserOk() {
      authenticate("user1");
      User user = userService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      List<Mezzage> mezzages = new ArrayList<>(inbox.getMezzages());
      Mezzage mezzage = mezzages.get(0);
      mezzageService.delete(mezzage);
      unauthenticate();
   }

   @Test
   public void deletingMessageTeacherOk() {
      authenticate("teacher1");
      Teacher user = teacherService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      List<Mezzage> mezzages = new ArrayList<>(inbox.getMezzages());
      Mezzage mezzage = mezzages.get(0);
      mezzageService.delete(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void deletingMessageUserNotOk() {
      authenticate("user1");
      User user = userService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "f");
      Assert.notNull(inbox);
      List<Mezzage> mezzages = new ArrayList<>(inbox.getMezzages());
      Mezzage mezzage = mezzages.get(0);
      mezzageService.delete(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void deletingMessageTeacherNotOk() {
      authenticate("teacher1");
      Teacher user = teacherService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "f");
      Assert.notNull(inbox);
      List<Mezzage> mezzages = new ArrayList<>(inbox.getMezzages());
      Mezzage mezzage = mezzages.get(0);
      mezzageService.delete(mezzage);
      unauthenticate();
   }

   //Move messages from one folder to another one
   @Test
   public void movingMessageUserOk() {
      authenticate("user1");
      User user = userService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      List<Mezzage> mezzages = new ArrayList<>(inbox.getMezzages());
      Mezzage mezzage = mezzages.get(0);
      Folder trashbox = actorService.folderByName(user, "Trashbox");
      Assert.notNull(trashbox);
      int size0 = trashbox.getMezzages().size();
      inbox.getMezzages().remove(mezzage);
      trashbox.getMezzages().add(mezzage);
      mezzage.setFolder(trashbox);
      org.junit.Assert.assertNotEquals(size0, trashbox.getMezzages().size());
      unauthenticate();
   }

   @Test
   public void movingMessageTeacherOk() {
      authenticate("teacher1");
      Teacher user = teacherService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "Inbox");
      Assert.notNull(inbox);
      List<Mezzage> mezzages = new ArrayList<>(inbox.getMezzages());
      Mezzage mezzage = mezzages.get(0);
      Folder trashbox = actorService.folderByName(user, "Trashbox");
      Assert.notNull(trashbox);
      int size0 = trashbox.getMezzages().size();
      inbox.getMezzages().remove(mezzage);
      trashbox.getMezzages().add(mezzage);
      mezzage.setFolder(trashbox);
      org.junit.Assert.assertNotEquals(size0, trashbox.getMezzages().size());
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void movingMessageUserNotOk() {
      authenticate("user1");
      User user = userService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "f");
      Assert.notNull(inbox);
      List<Mezzage> mezzages = new ArrayList<>(inbox.getMezzages());
      Mezzage mezzage = mezzages.get(0);
      Folder trashbox = actorService.folderByName(user, "Trashbox");
      Assert.notNull(trashbox);
      inbox.getMezzages().remove(mezzage);
      trashbox.getMezzages().add(mezzage);
      mezzage.setFolder(trashbox);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void movingMessageTeacherNotOk() {
      authenticate("teacher1");
      Teacher user = teacherService.findByPrincipal();
      Folder inbox = actorService.folderByName(user, "f");
      Assert.notNull(inbox);
      List<Mezzage> mezzages = new ArrayList<>(inbox.getMezzages());
      Mezzage mezzage = mezzages.get(0);
      Folder trashbox = actorService.folderByName(user, "Trashbox");
      Assert.notNull(trashbox);
      inbox.getMezzages().remove(mezzage);
      trashbox.getMezzages().add(mezzage);
      mezzage.setFolder(trashbox);
      unauthenticate();
   }

   //send 2 positive test cases from user to user, user to teacher, teacher to user and teacher to teacher.
   @Test
   public void userToUserSendMessageOk2() {
      authenticate("user2");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   @Test
   public void userToTeacherSendMessageOk2() {
      authenticate("user2");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   @Test
   public void teacherToUserSendMessageOk2() {
      authenticate("teacher2");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   @Test
   public void teacherToTeacherSendMessageOk2() {
      authenticate("teacher2");
      Mezzage mezzage = mezzageService.create();
      mezzage.setSubject("test");
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   //Negative test cases with empty subject from user to user, user to teacher, teacher to user and teacher to teacher.
   @Test(expected = IllegalArgumentException.class)
   public void userToUserSendMessageNotOkSubject2() {
      authenticate("user2");
      Mezzage mezzage = mezzageService.create();
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user2mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void userToTeacherSendMessageNotOkSubject2() {
      authenticate("user2");
      Mezzage mezzage = mezzageService.create();
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherToUserSendMessageNotOkSubject2() {
      authenticate("teacher2");
      Mezzage mezzage = mezzageService.create();
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("user2mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void teacherToTeacherSendMessageNotOkSubject2() {
      authenticate("teacher2");
      Mezzage mezzage = mezzageService.create();
      mezzage.setBody("tezt");
      mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
      mezzage.setReceiverEmail("teacher1mail@gmail.com");
      mezzage.setPriority(Priority.LOW);
      actorService.textMessage(mezzage);
      Assert.notNull(mezzage);
      unauthenticate();
   }
   //TODO: (A1) An actor who is authenticated as user or teacher must be able to reply and forward any message.


   //An actor who is authenticated as teacher, must be able to send a broadcast message to all the users assistants to a webinar.

   @Test
   public void broadcastMessageOk() {
      authenticate("teacher1");
      Teacher t = teacherService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(webinarService.myWebinars(t));
      Webinar webinar = webinars.get(0);
      Mezzage broadcast = mezzageService.create();
      broadcast.setBody("be");
      broadcast.setSubject("dsg");
      broadcast.setPriority(Priority.HIGH);
      mezzageService.save(broadcast);
      int size0 = webinar.getWebiMezzages().size();
      webinar.getWebiMezzages().add(broadcast);
      org.junit.Assert.assertNotEquals(size0, webinar.getWebiMezzages().size());
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void broadcastMessageNotOk() {
      authenticate(null);
      Teacher t = teacherService.findByPrincipal();
      List<Webinar> webinars = new ArrayList<>(webinarService.myWebinars(t));
      Webinar webinar = webinars.get(0);
      Mezzage broadcast = mezzageService.create();
      broadcast.setBody("be");
      broadcast.setSubject("dsg");
      broadcast.setPriority(Priority.HIGH);
      mezzageService.save(broadcast);
      int size0 = webinar.getWebiMezzages().size();
      webinar.getWebiMezzages().add(broadcast);
      org.junit.Assert.assertNotEquals(size0, webinar.getWebiMezzages().size());
      unauthenticate();
   }



}