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

import javax.transaction.Transactional;

import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.*;
import utilities.AbstractTest;

import java.util.ArrayList;
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
	private OtherService otherService;
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




	@Test
	public void testSearches(){

		List<Category> categoryList = new ArrayList<>(categoryService.findAll());

		System.out.println(searchService.questionsByKeyword("1"));
		System.out.println(searchService.questionsByKeywordAndCategory("1", categoryList.get(1)));

	}



	@Test
	public void testLogins(){

		authenticate("user1");

		otherService.findByPrincipal();



		authenticate(null);

	}


	@Test
	public void testAnswers(){

		authenticate("user1");

		List<Question> questions =  new ArrayList<>(questionService.findAll());

		Answer answer = new Answer();
		answer.setQuestion(questions.get(0));
		answer.setBanned(false);
		answer.setOwner(otherService.findByPrincipal());
		answer.setTitle("Prueba");
		answer.setDescription("Prueba");


		System.out.println(questions.get(0).getAnswers());

		questions.get(0).getAnswers().add(answer);

		System.out.println(questions.get(0).getAnswers());


		authenticate(null);

	}

	@Test
	public void dsafsdfs() {

      authenticate("user2");
      User user = userService.findByPrincipal();
      System.out.println(user.getAnswers());
      unauthenticate();

	}

	@Test
	public void testComment(){

		authenticate("user1");

		List<Webinar> webinars =  new ArrayList<>(webinarService.findAll());

		Comment comment = new Comment();
		comment.setTitle("GENERIC");
		comment.setText("GENERIC");
		comment.setCreationDate(new Date(System.currentTimeMillis()));
		comment.setOwner(otherService.findByPrincipal());
		comment.setWebinar(webinars.get(0));



		System.out.println(webinars.get(0).getComments());

		webinars.get(0).getComments().add(comment);

		System.out.println(webinars.get(0).getComments());


		authenticate(null);

	}

	// Ancillary methods ------------------------------------------------------

}
