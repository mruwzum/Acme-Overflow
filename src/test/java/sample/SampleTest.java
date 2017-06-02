/*
 * SampleTest.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package sample;

import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import security.Authority;
import services.*;
import utilities.AbstractTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@ContextConfiguration(locations = {
        "classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SampleTest extends AbstractTest {

    // System under test ------------------------------------------------------

    @Autowired
    private SearchService searchService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ActorService actorService;

    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private WebinarService webinarService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private MezzageService mezzageService;
    @Autowired
    private ActorRepository actorRepository;
    // Tests ------------------------------------------------------------------

    // The following are fictitious test cases that are intended to check that
    // JUnit works well in this project.  Just righ-click this class and run
    // it using JUnit.

    @Test
    public void samplePositiveTest() {
        Assert.isTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sampleNegativeTest() {
        Assert.isTrue(false);
    }

//
////    @Test
////    public void testSearches() {
////
////        List<Category> categoryList = new ArrayList<>(categoryService.findAll());
////
////        System.out.println(searchService.questionsByKeyword("1"));
////        System.out.println(searchService.questionsByKeywordAndCategory("1", categoryList.get(1)));
////
////    }
//
//
    @Test
    public void testLogins() {

        authenticate("user1");

        Authority authority = new Authority();
        authority.setAuthority("USER");

        System.out.println(userService.userNotModerator());
        System.out.println(userService.userNotBanned());


        authenticate(null);

    }
//
//
//    @Test
//    public void testAnswers() {
//
//        authenticate("user1");
//
//        List<Question> questions = new ArrayList<>(questionService.findAll());
//
//        Answer answer = new Answer();
//        answer.setQuestion(questions.get(0));
//        answer.setBanned(false);
//        answer.setOwner(actorService.findByPrincipal());
//        answer.setTitle("Prueba");
//        answer.setDescription("Prueba");
//
//
//        System.out.println(questions.get(0).getAnswers());
//
//        questions.get(0).getAnswers().add(answer);
//
//        System.out.println(questions.get(0).getAnswers());
//
//
//        authenticate(null);
//
//    }
//
//    @Test
//    public void dsafsdfs() {
//
//        authenticate("user1");
//
//        Collection<Folder> folders = actorService.getFolders();
//        System.out.println(folders);
//
//        authenticate(null);
//
//
//    }
//
//    @Test
//    public void testComment() {
//
//        authenticate("user1");
//
//        List<Webinar> webinars = new ArrayList<>(webinarService.findAll());
//
//        Comment comment = new Comment();
//        comment.setTitle("GENERIC");
//        comment.setText("GENERIC");
//        comment.setCreationDate(new Date(System.currentTimeMillis()));
//        comment.setOwner(actorService.findByPrincipal());
//        comment.setWebinar(webinars.get(0));
//
//
//        System.out.println(webinars.get(0).getComments());
//
//        webinars.get(0).getComments().add(comment);
//
//        System.out.println(webinars.get(0).getComments());
//
//
//        authenticate(null);
//
//    }
//
//
//    @Test
//    public void testMessage() {
//
//        authenticate("teacher1");
//
//       // Actor actor = actors.get(5);
//
//        Priority p = Priority.HIGH;
//        Mezzage mezzage = mezzageService.create();
//
//        mezzage.setSubject("HOLA");
//        mezzage.setBody("TOCHO");
//        mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
//        mezzage.setSender(actorService.findByPrincipal());
//        mezzage.setSendDate(new Date(System.currentTimeMillis()-1000));
//        mezzage.setReceiverEmail("teacher2mail@gmail.com");
//        mezzage.setPriority(p);
//
//
//
//        actorService.textMessage(mezzage);
//
//
//        //List<Folder> folders = new ArrayList<>(mezzage.getSender().getFolders());
//        Folder actor2 = actorRepository.folderByName(actorService.findByPrincipal(),"Outbox");
//
//        System.out.println(actor2.getMezzages());
//
//        authenticate(null);
//
//    }
//
//
//   @Test
//   public void deleteMessage() {
//
//      authenticate("teacher1");
//
//      // System.out.println(actorService.findByPrincipal());
//
//      Actor a = actorService.findByPrincipal();
//      List<Mezzage> mezzages = new ArrayList<>(actorService.allMessages(a));
//      System.out.println(mezzages);
//
//      mezzages.get(0).setReceiver(null);
//      mezzages.get(0).setSender(null);
//      mezzages.get(0).setFolder(null);
//      mezzageService.save(mezzages.get(0));
//
//      mezzageService.delete(mezzages.get(0));
//
//      System.out.println(actorService.allMessages(a));
//
//
//      authenticate(null);
//
//   }
//
//
//    @Test
//    public void setQuestionNull() {
//
//        authenticate("user1");
//
//        List<Question> questions = new ArrayList<>(userService.findByPrincipal().getQuestions());
//
////        List<Answer> answers = new ArrayList<>(answerService.findAll());
////
////        for (Answer answer : answers){
////            System.out.println(answer.getQuestion());
////        }
//
//        questionService.setQuestionNull(questions.get(0));
//
//
////        for (Answer answer : answers){
////            System.out.println(answer.getQuestion());
////        }
//
//        questions.get(0).setAnswers(new ArrayList<Answer>());
//        System.out.println(questions.get(0).getAnswers());
//
//        authenticate(null);
//
//    }

    // Ancillary methods ------------------------------------------------------

}
