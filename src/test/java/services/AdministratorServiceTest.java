/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package services;

import domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by mruwzum on 21/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class AdministratorServiceTest extends AbstractTest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private SearchService searchService;
    @Autowired
    private SearchCacheService searchCacheService;
    @Autowired
    private DutyService dutyService;
    @Autowired
    private CurriculaService curriculaService;

    @Autowired
    private EvaluationService evaluationService;

    @Before
    public void setUp() {
    }


    //An user authenticated as administrator must be able to manage the taxonomy og categories, which involves creating,
    // listing, moidfifying and deleting them.
    @Test
    public void createCategory() {
        authenticate("admin1");
        Category category = categoryService.create();
        category.setDescription("description");
        category.setName("name");
        Category category1 = categoryService.create();
        category1.setDescription("description1");
        category1.setName("son");
        categoryService.save(category1);
        Collection<Category> categories = new HashSet<>();
        categories.add(category1);
        Category c = categoryService.save(category);
        Assert.assertNotNull(c);
        unauthenticate();
    }

    @Test
    public void deleteCategory() {
        authenticate("admin1");
        List<Category> categories = new ArrayList<>(categoryService.findAll());
        Category cat = categories.get(0);
        categoryService.delete(cat);
        unauthenticate();
    }

    @Test
    public void listingCategory() {
        authenticate("admin1");
        List<Category> categories = new ArrayList<>(categoryService.findAll());
        org.springframework.util.Assert.notEmpty(categories);
        unauthenticate();
    }

    @Test
    public void modifyCategory() {
        authenticate("admin1");
        List<Category> categories = new ArrayList<>(categoryService.findAll());
        Category cat = categories.get(0);
        cat.setName("cagssdag");
        Category ca = categoryService.save(cat);
        org.springframework.util.Assert.notNull(ca);
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createCategoryNotOk() {
        authenticate(null);
        Category category = categoryService.create();
        Assert.assertNotNull(administratorService.findByPrincipal());
        Category category1 = categoryService.create();
        categoryService.save(category1);
        Category c = categoryService.save(category);
        Assert.assertNotNull(c);
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCategoryNotOk() {
        authenticate("admin1");
        Category cat = new Category();
        categoryService.delete(cat);
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void listingCategoryNotOk() {
        authenticate(null);
        Assert.assertNotNull(administratorService.findByPrincipal());
        List<Category> categories = new ArrayList<>(categoryService.findAll());
        org.springframework.util.Assert.notEmpty(categories);
        unauthenticate();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void modifyCategoryNotOk() {
        authenticate("admin1");
        List<Category> categories = new ArrayList<>(categoryService.findAll());
        Category cat = categories.get(555);
        cat.setName("");
        Category ca = categoryService.save(cat);
        org.springframework.util.Assert.notNull(ca);
        unauthenticate();
    }


    //An user authenticated as administrator must be able to change the number of searches
    // that are going to be saved on the system per user.
    @Test
    public void changeTheNumberOfSearchesPerUserOk() {
        authenticate("admin1");
        List<SearchCache> searchCaches = new ArrayList<>(searchCacheService.findAll());
        SearchCache searchCache = searchCaches.get(0);
        int cach0 = searchCache.getCacheValue();
        Collection<Search> search0 = searchService.trunkedSearch();
        int cach1 = 20;
        searchCache.setCacheValue(cach1);
        Assert.assertNotEquals(cach0, cach1);
        unauthenticate();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void changeTheNumberOfSearchesPerUserNotOk() {
        authenticate("admin1");
        List<SearchCache> searchCaches = new ArrayList<>(searchCacheService.findAll());
        SearchCache searchCache = searchCaches.get(2222);
        int cach0 = searchCache.getCacheValue();
        int cach1 = 20;
        searchCache.setCacheValue(cach1);
        Assert.assertNotEquals(cach0, cach1);
        unauthenticate();
    }


    //An user authenticated as administrator must be able to change fee charged on a webinar creation
    @Test
    public void changeDutyOk() {
        authenticate("admin1");
        List<Duty> duties = new ArrayList<>(dutyService.findAll());
        Duty duty = duties.get(0);
        int dut0 = duty.getDutyValue();
        int dut1 = 20000;
        duty.setDutyValue(dut1);
        Assert.assertNotEquals(dut0, duty.getDutyValue());
        unauthenticate();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void changeDutyNotOk() {
        authenticate(null);
        List<Duty> duties = new ArrayList<>();
        Duty duty = duties.get(0);
        int dut0 = duty.getDutyValue();
        int dut1 = 20000;
        duty.setDutyValue(dut1);
        Assert.assertNotEquals(dut0, duty.getDutyValue());
        unauthenticate();
    }

    //An user authenticated as administrator must be able to approve or deny a teacher's curricula
    @Test
    public void approveCurriculaOK() {
        authenticate("admin1");
        List<Curricula> curricula = new ArrayList<>(curriculaService.findAll());
        org.springframework.util.Assert.notEmpty(curricula);
        Curricula curricula1 = curricula.get(0);
        curricula1.setApprobed(true);
        Assert.assertTrue(curricula1.isApprobed());
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void approveCurriculaNotOk() {
        authenticate("admin1");
        List<Curricula> curricula = new ArrayList<>();
        org.springframework.util.Assert.notEmpty(curricula);
        Curricula curricula1 = curricula.get(0);
        curricula1.setApprobed(true);
        Assert.assertTrue(curricula1.isApprobed());
        unauthenticate();
    }

    @Test
    public void denyCurriculaOk() {
        authenticate("admin1");
        List<Curricula> curricula = new ArrayList<>(curriculaService.findAll());
        org.springframework.util.Assert.notEmpty(curricula);
        Curricula curricula1 = curricula.get(0);
        curricula1.setApprobed(false);
        Assert.assertTrue(! curricula1.isApprobed());
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void denyCurriculaNotOk() {
        authenticate("admin1");
        List<Curricula> curricula = new ArrayList<>();
        org.springframework.util.Assert.notEmpty(curricula);
        Curricula curricula1 = curricula.get(0);
        curricula1.setApprobed(false);
        Assert.assertTrue(! curricula1.isApprobed());
        unauthenticate();
    }

    //An administrator must be able to change the evaluation questions
    @Test
    public void changeEvalQOk() {
        authenticate("admin1");
        List<Evaluation> evaluations = new ArrayList<>(evaluationService.findAll());
        Evaluation evaluation = evaluations.get(0);
        List<EvaluationQuestion> evaluationQuestions = new ArrayList<>(evaluation.getEvaluationQuestions());
        EvaluationQuestion evQ = evaluationQuestions.get(0);
        String prevTitl = evQ.getTitle();
        String title = "dddddddd";
        evQ.setTitle(title);
        Assert.assertNotEquals(prevTitl, evQ.getTitle());
        unauthenticate();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void changeEvalQNotOk() {
        authenticate("admin1");
        List<Evaluation> evaluations = new ArrayList<>(evaluationService.findAll());
        Evaluation evaluation = evaluations.get(0);
        List<EvaluationQuestion> evaluationQuestions = new ArrayList<>();
        EvaluationQuestion evQ = evaluationQuestions.get(0);
        String prevTitl = evQ.getTitle();
        String title = "dddddddd";
        evQ.setTitle(title);
        Assert.assertNotEquals(prevTitl, evQ.getTitle());
        unauthenticate();
    }

}