package services;

import domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
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
   @Autowired
   private SearchService searchService;
   @Autowired
   private CategoryService categoryService;


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
      Collection<Mezzage> received2 = new HashSet<>();
      Collection<Mezzage> sended2 = new HashSet<>();
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
      Question question = new Question();
      question.setBanned(false);
      Category category = new Category();
      category.setDescription("esdfd");
      category.setName("dsfdf");
      question.setCategories(category);
      question.setCreatedDate(new Date(System.currentTimeMillis() - 100));
      Collection<String> pics = new HashSet<>();
      pics.add("http://pic.jpg");
      question.setPictures(pics);
      question.setSummary("sdfsdfsd");
      question.setTitle("dsfsdf");
      question.setOwner(user2);
      Collection<Answer> answers22 = new HashSet<>();
      Answer answer = new Answer();
      answer.setDescription("safsfd");
      answer.setDislikes(23);
      answer.setLikes(2222);
      answer.setBanned(false);
      answer.setPictures(pics);
      answer.setQuestion(question);
      answers22.add(answer);
      question.setAnswers(answers22);
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
      org.junit.Assert.assertNotNull(a);

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
      userService.registerAsUser(u);
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
      org.junit.Assert.assertNotNull(a);

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

   //Edit profile positive&negative cases changing name
   @Test
   public void userEditProfileOk() {
      authenticate("user1");
      String prevName = userService.findByPrincipal().getName();
      String newName = "perri";
      userService.findByPrincipal().setName(newName);
      org.junit.Assert.assertNotEquals(prevName, newName);
      unauthenticate();
   }

   @Test(expected = AssertionError.class)
   public void userEditProfileNotOk() {
      authenticate("user1");
      String prevName = userService.findByPrincipal().getName();
      String newName = userService.findByPrincipal().getName();
      userService.findByPrincipal().setName(newName);
      org.junit.Assert.assertNotEquals(prevName, newName);
      unauthenticate();
   }


   //Edit profile positive&negative cases changing owner name of his/her creditcard
   @Test
   public void userEditProfileCreditCardOk() {
      authenticate("user1a");

      String prevName = userService.findByPrincipal().getCreditCard().getHolderName();
      String newName = "perri";
      userService.findByPrincipal().getCreditCard().setHolderName(newName);
      org.junit.Assert.assertNotEquals(prevName, newName);
      unauthenticate();
   }

   @Test(expected = AssertionError.class)
   public void userEditProfileCreditCardNotOk() {
      authenticate("user1a");
      String prevName = userService.findByPrincipal().getCreditCard().getHolderName();
      String newName = userService.findByPrincipal().getCreditCard().getHolderName();
      userService.findByPrincipal().getCreditCard().setHolderName(newName);
      org.junit.Assert.assertNotEquals(prevName, newName);
      unauthenticate();
   }

   //A registered user is able to create a question on the system, positive&negative test cases
   @Test
   public void createQuestionOk() {
      authenticate("user1a");

      Question question = questionService.create();
      question.setTitle("questionTes");
      question.setSummary("sum");
      question.setCreatedDate(new Date(System.currentTimeMillis() - 100));
      question.setBanned(false);
      Category category = new Category();
      category.setName("aas");
      category.setDescription("dsfsdf");
      question.setCategories(category);
      Collection<String> pics = new ArrayList<>();
      question.setPictures(pics);
      question.setOwner(userService.findByPrincipal());
      Question res = questionService.save(question);
      Assert.notNull(res);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void createQuestionNotOk() {
      authenticate("teacher8");
      Question question = questionService.create();
      question.setSummary("sum");
      question.setBanned(false);
      Category category = new Category();
      category.setName("aas");
      category.setDescription("dsfsdf");
      question.setCategories(category);
      Collection<String> pics = new ArrayList<>();
      question.setPictures(pics);
      questionService.save(question);
      unauthenticate();
   }

   //A registered user can answer a question of any user positive & negative test cases
   @Test
   public void answerAQuestionOk() {
      authenticate("user1a");
      List<Question> questions = new ArrayList<>(questionService.findAll());
      Question qToAns = questions.get(0);
      Answer ans = answerService.create();
      ans.setBanned(false);
      ans.setDescription("dsfsdf");
      ans.setOwner(userService.findByPrincipal());
      ans.setTitle("sdgdfgdfsg");
      qToAns.getAnswers().add(ans);
      unauthenticate();
   }

   @Test(expected = IndexOutOfBoundsException.class)
   public void answerAQuestionNotOk() {
      authenticate("user1a");
      List<Question> questions = new ArrayList<>(questionService.findAll());
      Question qToAns = questions.get(110);
      Answer ans = answerService.create();
      qToAns.getAnswers().add(ans);
      unauthenticate();
   }

   //An authenticated user can rate any answer on the System positive&negative cases
   @Test
   public void rateAnAnswer() {
      authenticate("user1a");
      List<Question> questions = new ArrayList<>(questionService.findAll());
      Question qToAns = questions.get(1);
      List<Answer> answers = new ArrayList<>(qToAns.getAnswers());
      Answer toRate = answers.get(0);
      int likes = toRate.getLikes();
      toRate.setLikes(likes + 1);
      answerService.save(toRate);
      org.junit.Assert.assertNotEquals(likes, toRate.getLikes());
      unauthenticate();
   }

   @Test(expected = IndexOutOfBoundsException.class)
   public void rateAnAnswerNotOk() {
      authenticate("user1a");
      List<Question> questions = new ArrayList<>(questionService.findAll());
      Question qToAns = questions.get(777);
      List<Answer> answers = new ArrayList<>(qToAns.getAnswers());
      Answer toRate = answers.get(0);
      int likes = toRate.getLikes();
      toRate.setLikes(likes + 1);
      answerService.save(toRate);
      org.junit.Assert.assertNotEquals(likes, toRate.getLikes());
      unauthenticate();
   }

   @Test
   public void unrateAnAnswer() {
      authenticate("user1a");
      List<Question> questions = new ArrayList<>(questionService.findAll());
      Question qToAns = questions.get(1);
      List<Answer> answers = new ArrayList<>(qToAns.getAnswers());
      Answer toRate = answers.get(0);
      int dislikes = toRate.getDislikes();
      toRate.setDislikes(dislikes + 1);
      answerService.save(toRate);
      org.junit.Assert.assertNotEquals(dislikes, toRate.getLikes());
      unauthenticate();
   }

   @Test(expected = IndexOutOfBoundsException.class)
   public void unrateAnAnswerNotOk() {
      authenticate("user1a");
      List<Question> questions = new ArrayList<>(questionService.findAll());
      Question qToAns = questions.get(777);
      List<Answer> answers = new ArrayList<>(qToAns.getAnswers());
      Answer toRate = answers.get(0);
      int dislikes = toRate.getDislikes();
      toRate.setDislikes(dislikes + 1);
      answerService.save(toRate);
      org.junit.Assert.assertNotEquals(dislikes, toRate.getLikes());
      unauthenticate();
   }

   // An authenticated user can make a search on the system for cats or kw positive & negative test cases

   @Test
   public void searchPositive() {
      authenticate("user1a");

      List<Category> categories = new ArrayList<>(categoryService.findAll());
      Collection<Question> results = searchService.questionsByKeywordAndCategory("", categories.get(0));
      Assert.notEmpty(results);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void searchNegative() {
      authenticate("user1a");
      List<Category> categories = new ArrayList<>(categoryService.findAll());
      Collection<Question> results = searchService.questionsByKeywordAndCategory("asdfdsf", categories.get(0));
      Assert.notEmpty(results);
      unauthenticate();
   }


   // An authenticated user can List all the registered users and watch their profiles

   @Test
   public void ListAllUsersAndProfileOk() {
      authenticate("user1a");
      List<User> users = new ArrayList<>(userService.findAll());
      User u = users.get(0);
      org.junit.Assert.assertNotNull(u.getName());
      unauthenticate();
   }

   @Test(expected = IndexOutOfBoundsException.class)
   public void listAllUsersAndProfilesNotOk() {
      authenticate("user1a");
      List<User> users = new ArrayList<>();
      User u = users.get(0);
      org.junit.Assert.assertNotNull(u.getName());
      unauthenticate();
   }


   // A moderator must be able to list all users

   @Test
   public void ListAllUsersAndProfileOkModetator() {
      authenticate("moderator1");
      List<User> users = new ArrayList<>(userService.findAll());
      Assert.notEmpty(users);
      unauthenticate();
   }

   @Test(expected = IndexOutOfBoundsException.class)
   public void listAllUsersAndProfilesNotOkModerator() {
      authenticate("moderator1");
      List<User> users = new ArrayList<>();
      User u = users.get(0);
      org.junit.Assert.assertNotNull(u);
      unauthenticate();
   }



   // a non authenticated user must be able to register as a teacher positive & negative cases
   @Test
   public void registerAsTeacher() {
      Teacher u = teacherService.create();
      UserAccount userAccount = new UserAccount();
      userAccount.setUsername("dfasdfsdf");
      userAccount.setPassword("safsafd");
      u.setUserAccount(userAccount);
      u.setPhoneNumber("956789543");
      u.setName("ussdad");
      u.setSurname("dsfsdf");
      u.setEmail("sadsd@mail.com");
      Curricula curricula = curriculaService.create();
      curricula.setExperienceSection("sdasdfsdf");
      curricula.setEducationSection("gsdgsdgfsdg");
      curricula.setHobbiesSection("fgdfgadgf");
      curricula.setPhoto("http://www.pic.png");
      Collection<String> tefd = new HashSet<>();
      tefd.add("dsafsdfds");
      curricula.setReferencias(tefd);
      curricula.setOwner(u);
      u.setCurricula(curricula);
      curriculaService.save(curricula);
      Actor a = userService.registerAsTeacher(u);
      org.junit.Assert.assertNotNull(a);
   }

   @Test(expected = NullPointerException.class)
   public void registerAsTeacherNotOk() {
      Teacher u = teacherService.create();
      UserAccount userAccount = new UserAccount();
      userAccount.setUsername("dfasdfsdf");
      userAccount.setPassword("safsafd");
      u.setUserAccount(userAccount);
      u.setPhoneNumber("956789543");
      u.setName("ussdad");
      u.setSurname("dsfsdf");
      u.setEmail("sadsd@mail.com");
      Actor a = userService.registerAsTeacher(u);
      org.junit.Assert.assertNotNull(a);
   }

   //A moderator is able to ban or unban a user positive & negative cases for both cases


   @Test
   public void banUserOk() {
      authenticate("moderator1");
      List<User> users = new ArrayList<>(userService.findAll());
      User u = users.get(0);
      Boolean res = userService.banUser(u);
      Assert.isTrue(res);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void banUserNotOk() {
      authenticate("moderator1");
      List<User> users = new ArrayList<>(userService.findAll());
      User u = users.get(0);
      userService.banUser(u);
      Boolean res = userService.banUser(u);
      Assert.isTrue(res);
      unauthenticate();
   }
   @Test
   public void unbanUserOk() {
      authenticate("moderator1");
      List<User> users = new ArrayList<>(userService.findAll());
      User u = users.get(0);
      userService.banUser(u);
      Boolean res = userService.unbanUser(u);
      Assert.isTrue(res);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void unbanUserNotOk() {
      authenticate("moderator1");
      List<User> users = new ArrayList<>(userService.findAll());
      User u = users.get(0);
      Boolean res = userService.unbanUser(u);
      Assert.isTrue(!res);
      unauthenticate();
   }


   //A moderator is able to ban or unban a question positive & negative cases

   @Test
   public void banQuestionOk() {
      authenticate("moderator1");
      List<Question> users = new ArrayList<>(questionService.notBannedQuestions());
      int size0 = users.size();
      Question u = users.get(0);
      Boolean res = questionService.banQuestion(u);
      Assert.isTrue(res);
      List<Question> users2 = new ArrayList<>(questionService.notBannedQuestions());
      int size1 = users2.size();
      org.junit.Assert.assertNotEquals(size0, size1);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void banCuestionNotOk() {
      authenticate("moderator1");
      List<Question> users = new ArrayList<>(questionService.findAll());
      Question u = users.get(0);
      questionService.banQuestion(u);
      Boolean res = questionService.banQuestion(u);
      Assert.isTrue(res);
      unauthenticate();
   }


   //A moderator is able to ban or unban a answer positive & negative cases
   @Test
   public void banAnsOk() {
      authenticate("moderator1");
      List<Question> users = new ArrayList<>(questionService.notBannedQuestions());
      Assert.isTrue(questionService.banQuestion(users.get(0)));
      List<Answer> answers = new ArrayList<>(users.get(0).getAnswers());
      int size0 = answers.size();
      List<Question> users2 = new ArrayList<>(questionService.notBannedQuestions());
      List<Answer> answers2 = new ArrayList<>(users2.get(0).getAnswers());
      int size1 = answers2.size();
      org.junit.Assert.assertNotEquals(size0, size1);
      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void banAnsNotOk() {
      authenticate("moderator1");
      List<Question> users = new ArrayList<>(questionService.notBannedQuestions());
      List<Answer> answers = new ArrayList<>(users.get(0).getAnswers());
      answerService.banAnswer(answers.get(0));
      Boolean res = answerService.banAnswer(answers.get(0));
      Assert.isTrue(res);
      unauthenticate();
   }
}