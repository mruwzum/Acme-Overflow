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

import domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.ActorService;
import services.CategoryService;
import services.OtherService;
import services.SearchService;
import utilities.AbstractTest;

import java.util.ArrayList;
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
	// Ancillary methods ------------------------------------------------------

}
