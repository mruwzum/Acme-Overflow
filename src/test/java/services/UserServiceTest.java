package services;

import domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import security.Authority;
import security.UserAccount;
import security.UserAccountService;
import utilities.AbstractTest;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by mruwzum on 21/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional

public class UserServiceTest extends AbstractTest {

   @Autowired
   private BannerService bannerService;
   @Autowired
   private ActorService actorService;
   @Autowired
   private UserService userService;
   @Autowired
   private UserAccountService userAccountService;
   @Autowired
   private FolderService folderService;
   @Autowired
   private QuestionService questionService;
   @Autowired
   private AnswerService answerService;
   @Autowired
   private CreditCardService creditCardService;
   @Autowired
   private TeacherService teacherService;
   @Autowired
   private CurriculaService curriculaService;


   @Before
   public void setUp() {

////USER1A
      User user1 = new User();
      user1.setBanned(false);
      user1.setEmail("user1a@email.com");
      user1.setSurname("iser");
      user1.setName("dsfasd");
      user1.setPhoneNumber("65674322");
      Collection<Bill> bills = new HashSet<>();
      user1.setBills(bills);
      CreditCard creditCard = new CreditCard();
      creditCard.setHolderName("user1a");
      creditCard.setMonth(2);
      creditCard.setCVV("222");
      creditCard.setType(CreditCardType.AMEX);
      creditCard.setYear(2020);
      creditCard.setNumber("54275498043695577");
      CreditCard aux = creditCardService.save(creditCard);
      user1.setCreditCard(aux);
      Authority autoh = new Authority();
      autoh.setAuthority("USER");
      UserAccount res = new UserAccount();
      res.addAuthority(autoh);
      res.setUsername("user1a");
      Md5PasswordEncoder encoder;
      encoder = new Md5PasswordEncoder();
      String hash = encoder.encodePassword("user1a", null);
      res.setPassword(hash);
      user1.setUserAccount(res);
      Assert.notNull(user1.getUserAccount().getAuthorities(), "authorities null al registrar");
      User resu = userService.save(user1);
//      Collection<Mezzage> received = new HashSet<>();
//      Collection<Mezzage> sended = new HashSet<>();
//      Collection<Folder> folders = new HashSet<>();
//      Folder inbox = folderService.create();
//      inbox.setName("Inbox");
//      inbox.setOwner(resu);
//      Collection<Mezzage> innnn = new HashSet<>();
//      inbox.setMezzages(innnn);
//      Folder outbox = folderService.create();
//      outbox.setName("Outbox");
//      outbox.setOwner(resu);
//      Collection<Mezzage> ouuuu = new HashSet<>();
//      outbox.setMezzages(ouuuu);
//      Folder spambox = folderService.create();
//      spambox.setName("Spambox");
//      spambox.setOwner(resu);
//      Collection<Mezzage> spaaaam = new HashSet<>();
//      spambox.setMezzages(spaaaam);
//      Folder trashBox = folderService.create();
//      trashBox.setName("Trashbox");
//      trashBox.setOwner(resu);
//      Collection<Mezzage> trashhh = new HashSet<>();
//      trashBox.setMezzages(trashhh);
//      folders.add(inbox);
//      folders.add(outbox);
//      folders.add(spambox);
//      folders.add(trashBox);
//      folderService.save(inbox);
//      folderService.save(outbox);
//      folderService.save(spambox);
//      folderService.save(trashBox);
//      resu.setFolders(folders);
//      resu.setReceivedMezzages(received);
//      resu.setSendedMezzages(sended);
      Collection<Answer> answers = new HashSet<>();
      Collection<Question> questions = new HashSet<>();
      Collection<Comment> comments = new HashSet<>();
      resu.setQuestions(questions);
      resu.setAnswers(answers);
      resu.setComments(comments);
      userService.save(resu);
//
//  USER2A
      User user2 = new User();
      user2.setBanned(false);
      user2.setEmail("user2a@email.com");
      user2.setSurname("iser");
      user2.setName("user2a");
      user2.setPhoneNumber("65674322");
      Collection<Bill> bills2 = new HashSet<>();
      user2.setBills(bills2);
      CreditCard creditCard2 = new CreditCard();
      creditCard2.setHolderName("user2a");
      creditCard2.setMonth(2);
      creditCard2.setCVV("222");
      creditCard2.setType(CreditCardType.AMEX);
      creditCard2.setYear(2020);
      creditCard2.setNumber("54275498043695577");
      CreditCard aux2 = creditCardService.save(creditCard2);
      user2.setCreditCard(aux2);
      Authority autoh2 = new Authority();
      autoh2.setAuthority("USER");
      UserAccount res1 = new UserAccount();
      res1.addAuthority(autoh2);
      res1.setUsername("user2a");
      Md5PasswordEncoder encoder1;
      encoder1 = new Md5PasswordEncoder();
      String hash1 = encoder1.encodePassword("user2a", null);
      res1.setPassword(hash1);
      user2.setUserAccount(res1);
      Assert.notNull(user2.getUserAccount().getAuthorities(), "authorities null al registrar");
      Collection<Folder> folders3 = new HashSet<>();
      user2.setFolders(folders3);
      User resu2 = userService.save(user2);


//      Folder inbox2 = folderService.create();
//      inbox2.setName("inbox");
//     // Folder ddfsdf  = folderService.save(inbox2);
////       inbox2.setOwner(user2);
//
//      resu2.getFolders().add(inbox2);
      //Collection<Mezzage> innnn2 = new HashSet<>();
      //ddfsdf.setMezzages(innnn2);
//      Folder outbox2 = folderService.create();
//      outbox2.setName("outbox");
//      outbox2.setOwner(user2);
//      Collection<Mezzage> ouuuu2 = new HashSet<>();
//      outbox2.setMezzages(ouuuu2);
//      Folder spambox2 = folderService.create();
//      spambox2.setName("spambox");
//      spambox2.setOwner(user2);
//      Collection<Mezzage> spaaaam2 = new HashSet<>();
//      spambox2.setMezzages(spaaaam2);
//      Folder trashBox2 = folderService.create();
//      trashBox2.setName("trashbox");
//      trashBox2.setOwner(user2);
//      Collection<Mezzage> trashhh2 = new HashSet<>();
//      trashBox2.setMezzages(trashhh2);

//      folderService.save(inbox2);
//      folderService.save(outbox2);
//      folderService.save(spambox2);
//      folderService.save(trashBox2);
//
//      user2.getFolders().add(inbox2);
//      user2.getFolders().add(outbox2);
//      user2.getFolders().add(spambox2);
//      user2.getFolders().add(trashBox2);




      Collection<Mezzage> received2 = new HashSet<>();
      Collection<Mezzage> sended2 = new HashSet<>();
      Collection<Folder> folders2 = new HashSet<>();


      //resu2.setFolders(folders2);
      //resu2.getFolders().addAll(folders2);
      resu2.setReceivedMezzages(received2);
      resu2.setSendedMezzages(sended2);
      Collection<Answer> answers2 = new HashSet<>();
      Collection<Question> questions2 = new HashSet<>();
      Collection<Comment> comments2 = new HashSet<>();
      resu2.setQuestions(questions2);
      resu2.setAnswers(answers2);
      resu2.setComments(comments2);
      userService.save(resu2);


//      //teacher2a
      Teacher teacher = new Teacher();
      teacher.setEmail("teachera@email.com");
      teacher.setSurname("iser");
      teacher.setName("teachera");
      teacher.setPhoneNumber("65674322");


      CreditCard credit3 = new CreditCard();
      credit3.setHolderName("teachera");
      credit3.setMonth(2);
      credit3.setCVV("222");
      credit3.setType(CreditCardType.AMEX);
      credit3.setYear(2020);
      credit3.setNumber("54275498043695577");
      CreditCard aux3 = creditCardService.save(credit3);
      teacher.setCreditCard(aux3);
      Authority autoh3 = new Authority();
      autoh3.setAuthority("USER");
      UserAccount res2 = new UserAccount();
      res2.addAuthority(autoh3);
      res2.setUsername("teacher1a");
      Md5PasswordEncoder encoder2;
      encoder2 = new Md5PasswordEncoder();
      String hash2 = encoder2.encodePassword("teachera", null);
      res2.setPassword(hash2);
      teacher.setUserAccount(res2);
      Assert.notNull(teacher.getUserAccount().getAuthorities(), "authorities null al registrar");
      Teacher resu3 = teacherService.save(teacher);
//      Collection<Mezzage> received3 = new HashSet<>();
//      Collection<Mezzage> sended3 = new HashSet<>();
//      Collection<Folder> folders4 = new HashSet<>();
//      Folder inbox3 = folderService.create();
//      inbox3.setName("inbox");
//      inbox3.setOwner(resu3);
//      Collection<Mezzage> innnn3 = new HashSet<>();
//      inbox3.setMezzages(innnn3);
//      Folder outbox3 = folderService.create();
//      outbox3.setName("outbox");
//      outbox3.setOwner(resu3);
//      Collection<Mezzage> ouuuu3 = new HashSet<>();
//      outbox3.setMezzages(ouuuu3);
//      Folder spambox3 = folderService.create();
//      spambox3.setName("spambox");
//      spambox3.setOwner(resu3);
//      Collection<Mezzage> spaaaam3 = new HashSet<>();
//      spambox3.setMezzages(spaaaam3);
//      Folder trashBox3 = folderService.create();
//      trashBox3.setName("trashbox");
//      trashBox3.setOwner(resu3);
//      Collection<Mezzage> trashhh3 = new HashSet<>();
//      trashBox3.setMezzages(trashhh3);
//      folders4.add(inbox3);
//      folders4.add(outbox3);
//      folders4.add(spambox3);
//      folders4.add(trashBox3);
//      folderService.save(inbox3);
//      folderService.save(outbox3);
//      folderService.save(spambox3);
//      folderService.save(trashBox3);
//      resu3.setFolders(folders4);
//      resu3.setReceivedMezzages(received3);
//      resu3.setSendedMezzages(sended3);


      Collection<Answer> answers3 = new HashSet<>();
      Collection<Comment> comments3 = new HashSet<>();
      resu3.setAnswers(answers3);
      resu3.setComments(comments3);
      Curricula curricula = new Curricula();
      curricula.setApprobed(true);

      Collection<String> referemcoas = new HashSet<>();
      curricula.setReferencias(referemcoas);
      curricula.setPhoto("http://www.goto.png");
      curricula.setHobbiesSection("dsfadsfsdafsdf");
      curricula.setEducationSection("dfgdfgdagfsg");
      curricula.setExperienceSection("sdfsdfdf");
      curricula.setOwner(resu3);
      resu3.setCurricula(curricula);
      curriculaService.save(curricula);


//      Question question = new Question();
//      question.setBanned(false);
//      Category category = new Category();
//      category.setDescription("esdfd");
//      category.setName("dsfdf");
//      question.setCategories(category);
//      question.setCreatedDate(new Date(System.currentTimeMillis() - 100));
//      Collection<String> pics = new HashSet<>();
//      pics.add("http://pic.jpg");
//      question.setPictures(pics);
//      question.setSummary("sdfsdfsd");
//      question.setTitle("dsfsdf");
//      question.setOwner(user2);
//      Collection<Answer> answers22 = new HashSet<>();
//      Answer answer = new Answer();
//
//      answer.setDescription("safsfd");
//      answer.setDislikes(23);
//      answer.setLikes(2222);
//      answer.setBanned(false);
//      answer.setPictures(pics);
//      answer.setQuestion(question);
//      answers22.add(answer);
//      question.setAnswers(answers22);
//      questionService.save(question);



   }


   //Register to the system as user Positive&Negative with Credit card
   @Test
   public void registerAsUserOkWithCreditCard() {
      User u = userService.create();
      UserAccount userAccount = new UserAccount();
      userAccount.setUsername("dfasdfsdf");
      userAccount.setPassword("safsafd");
      u.setUserAccount(userAccount);
      u.setPhoneNumber("956789543");
      u.setName("ussdad");
      u.setSurname("dsfsdf");
      u.setEmail("sadsd@mail.com");
      CreditCard cc = new CreditCard();
      cc.setNumber("54275498043695577");
      cc.setYear(2020);
      cc.setType(CreditCardType.DINNERS);
      cc.setCVV("443");
      cc.setMonth(3);
      cc.setHolderName("ussdad");
      u.setCreditCard(cc);
      Actor a = userService.registerAsUser(u);


   }

   @Test(expected = NullPointerException.class)
   public void registerAsUserNotOkWithCreditCard() {
      User u = userService.create();
      UserAccount userAccount = new UserAccount();
      userAccount.setUsername("dfasdfsdf");
      userAccount.setPassword("safsafd");
      u.setPhoneNumber("956789543");
      u.setName("ussdad");
      u.setSurname("dsfsdf");
      u.setEmail("sadsd@mail.com");
      CreditCard cc = new CreditCard();
      cc.setNumber("54275498043695577");
      cc.setYear(2020);
      cc.setType(CreditCardType.DINNERS);
      cc.setCVV("443");
      cc.setMonth(3);
      cc.setHolderName("ussdad");
      u.setCreditCard(cc);
      Actor a = userService.registerAsUser(u);
      userService.flush();
   }

   // Register to the system as user without credit card

   @Test
   public void registerAsUserOk() {
      User u = userService.create();
      UserAccount userAccount = new UserAccount();
      userAccount.setUsername("dfasdfsdf");
      userAccount.setPassword("safsafd");
      u.setUserAccount(userAccount);
      u.setPhoneNumber("956789543");
      u.setName("ussdad");
      u.setSurname("dsfsdf");
      u.setEmail("sadsd@mail.com");
      Actor a = userService.registerAsUser(u);

   }

   @Test(expected = NullPointerException.class)
   public void registerAsUserNotOk() {
      User u = userService.create();
      UserAccount userAccount = new UserAccount();
      userAccount.setUsername("dfasdfsdf");
      userAccount.setPassword("safsafd");
      u.setPhoneNumber("956789543");
      u.setName("ussdad");
      u.setSurname("dsfsdf");
      u.setEmail("sadsd@mail.com");
      Actor a = userService.registerAsUser(u);
      userService.flush();

   }

   //Browse the list of questions and navigate to their ansewers positive&negative case tests

   @Test
   public void questionsListAndAnswersOk() {
      Set<Question> questions = new HashSet<>();
      questions.addAll(questionService.notBannedQuestions());
      Assert.notEmpty(questions);
      Collection<Answer> answers = new HashSet<>();
      for (Question q : questions) {
         answers.addAll(q.getAnswers());
      }
      Assert.notEmpty(answers);
   }


   @Test(expected = IllegalArgumentException.class)
   public void questionsListAndAnswersNotOk() {
      Set<Question> questions = new HashSet<>();
      questions.addAll(questionService.notBannedQuestions());
      questions.removeAll(questions);
      Assert.notEmpty(questions);
      Collection<Answer> answers = new HashSet<>();
      for (Question q : questions) {
         answers.addAll(q.getAnswers());
      }
      Assert.notEmpty(answers);
   }

   //Browse the list of questions ordered by popularity
   @Test
   public void questionsListPopularityOk() {
      Set<Question> questions = new HashSet<>();
      questions.addAll(questionService.listPopular());
      Assert.notEmpty(questions);
   }


   @Test(expected = IllegalArgumentException.class)
   public void questionsListPopularitysNotOk() {
      Set<Question> questions = new HashSet<>();
      questions.addAll(questionService.listPopular());
      questions.removeAll(questions);
      Assert.notEmpty(questions);
      Collection<Answer> answers = new HashSet<>();
      for (Question q : questions) {
         answers.addAll(q.getAnswers());
      }
      Assert.notEmpty(answers);
   }


   @Test
   public void registerAsTeacher() {
   }

   @Test
   public void banUser() {
   }

   @Test
   public void unbanUser() {
   }

}