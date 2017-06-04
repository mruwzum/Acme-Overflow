/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

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
    @Autowired
    private LearningMaterialService learningMaterialService;
    @Autowired
    private ModuleService moduleService;

    @Before
    public void setUp() {
        LearningMaterial learningMaterial = learningMaterialService.create();
        learningMaterial.setAttachmentsURLs("http://www.video.mov");
        learningMaterial.setTitle("title");
        learningMaterial.setType(LearningMaterialType.VIDEO);
        Module module = moduleService.create();
        module.setDescription("des");
        module.setTitle("title");
        Collection<LearningMaterial> learningMaterials = new HashSet<>();
        learningMaterials.add(learningMaterial);
        module.setLearningMaterials(learningMaterials);
        learningMaterial.setModule(module);
        learningMaterialService.save(learningMaterial);


        LearningMaterial learningMaterial2 = learningMaterialService.create();
        learningMaterial2.setAttachmentsURLs("http://www.video.mov");
        learningMaterial2.setTitle("title");
        learningMaterial2.setType(LearningMaterialType.VIDEO);
        Module module2 = moduleService.create();
        module2.setDescription("des");
        module2.setTitle("title");
        Collection<LearningMaterial> learningMaterials2 = new HashSet<>();
        learningMaterials2.add(learningMaterial2);
        module2.setLearningMaterials(learningMaterials2);
        learningMaterial2.setModule(module2);
        learningMaterialService.save(learningMaterial2);
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

    // edit learning materials from a teacher positive and negative test cases

    @Test
    public void listLearningMaterials() {
        authenticate("teacher1");
        List<LearningMaterial> learningMaterials = new ArrayList<>(learningMaterialService.findAll());
        Assert.notEmpty(learningMaterials);
        unauthenticate();
    }

    @Test
    public void createLearningMaterials() {
        authenticate("teacher1");
        LearningMaterial learningMaterial = learningMaterialService.create();
        learningMaterial.setAttachmentsURLs("http://www.video.mov");
        learningMaterial.setTitle("title");
        learningMaterial.setType(LearningMaterialType.VIDEO);
        Module module = moduleService.create();
        module.setDescription("des");
        module.setTitle("title");
        Collection<LearningMaterial> learningMaterials = new HashSet<>();
        learningMaterials.add(learningMaterial);
        module.setLearningMaterials(learningMaterials);
        learningMaterial.setModule(module);
        learningMaterialService.save(learningMaterial);
        unauthenticate();
    }

    @Test
    public void editLearningMaterials() {
        authenticate("teacher1");
        List<LearningMaterial> learningMaterials = new ArrayList<>(learningMaterialService.findAll());
        LearningMaterial learningMaterial = learningMaterials.get(0);
        String name = learningMaterial.getTitle();
        String nname = "fadfadfsdf";
        learningMaterial.setTitle(nname);
        learningMaterialService.save(learningMaterial);
        org.junit.Assert.assertNotEquals(name, learningMaterial.getTitle());
        unauthenticate();
    }

    @Test
    public void deleteLearningMaterials() {
        authenticate("teacher1");
        List<LearningMaterial> learningMaterials = new ArrayList<>(learningMaterialService.findAll());
        LearningMaterial learningMaterial = learningMaterials.get(0);
        learningMaterialService.delete(learningMaterial);
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void listLearningMaterialsNotOk() {
        authenticate("teacher1");
        List<LearningMaterial> learningMaterials = new ArrayList<>();
        Assert.notEmpty(learningMaterials);
        unauthenticate();
    }

    @Test
    public void createLearningMaterialsNotOk() {
        authenticate("teacher1");
        LearningMaterial learningMaterial = learningMaterialService.create();
        Module module = moduleService.create();
        Collection<LearningMaterial> learningMaterials = new HashSet<>();
        learningMaterials.add(learningMaterial);
        module.setLearningMaterials(learningMaterials);
        learningMaterial.setModule(module);
        learningMaterialService.save(learningMaterial);
        unauthenticate();
    }

    @Test(expected = AssertionError.class)
    public void editLearningMaterialsNotOk() {
        authenticate("teacher1");
        List<LearningMaterial> learningMaterials = new ArrayList<>(learningMaterialService.findAll());
        LearningMaterial learningMaterial = learningMaterials.get(0);
        String name = learningMaterial.getTitle();
        String nname = "fadfadfsdf";
        learningMaterialService.save(learningMaterial);
        org.junit.Assert.assertNotEquals(name, learningMaterial.getTitle());
        unauthenticate();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteLearningMaterialsNotOk() {
        authenticate("teacher1");
        List<LearningMaterial> learningMaterials = new ArrayList<>(learningMaterialService.findAll());
        LearningMaterial learningMaterial = learningMaterials.get(50000);
        learningMaterialService.delete(learningMaterial);
        unauthenticate();
    }


    // and authenticated teacher is able to edit modules positive and negative test cases

    @Test
    public void listModules() {
        authenticate("teacher1");
        List<Module> modules = new ArrayList<>(moduleService.findAll());
        Assert.notEmpty(modules);
        unauthenticate();
    }

    @Test
    public void createModules() {
        authenticate("teacher1");
        Module module = moduleService.create();
        module.setDescription("des");
        module.setTitle("title");
        moduleService.save(module);
        unauthenticate();
    }

    @Test
    public void editModules() {
        authenticate("teacher1");
        List<Module> modules = new ArrayList<>(moduleService.findAll());
        Module module = modules.get(0);
        String name = module.getTitle();
        String nname = "fadfadfsdf";
        module.setTitle(nname);
        moduleService.save(module);
        org.junit.Assert.assertNotEquals(name, module.getTitle());
        unauthenticate();
    }

    @Test
    public void deleteModules() {
        authenticate("teacher1");
        List<Module> modules = new ArrayList<>(moduleService.findAll());
        Module module = modules.get(0);
        moduleService.delete(module);
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void listModulesNotOk() {
        authenticate("teacher1");
        List<Module> modules = new ArrayList<>();
        Assert.notEmpty(modules);
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createModulesNotOk() {
        authenticate(null);
        Teacher t = teacherService.findByPrincipal();
        Module module = moduleService.create();
        moduleService.save(module);
        unauthenticate();
    }

    @Test(expected = AssertionError.class)
    public void editModulesNotOk() {
        authenticate("teacher1");
        List<Module> modules = new ArrayList<>(moduleService.findAll());
        Module module = modules.get(0);
        String name = module.getTitle();
        moduleService.save(module);
        org.junit.Assert.assertNotEquals(name, module.getTitle());
        unauthenticate();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteModulesNotOk() {
        authenticate("teacher1");
        List<Module> learningMaterials = new ArrayList<>(moduleService.findAll());
        Module learningMaterial = learningMaterials.get(50000);
        moduleService.delete(learningMaterial);
        unauthenticate();
    }

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

    //An user registered as a teacher must be able to edit & delete them
    @Test
    public void deleteWebinarOk() {
        authenticate("teacher1");
        List<Webinar> webinars = new ArrayList<>(webinarService.myWebinars(teacherService.findByPrincipal()));
        Assert.notEmpty(webinars);
        Webinar webinar = webinars.get(0);
        webinarService.delete(webinar);
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteWebinarNotOk() {
        authenticate(null);
        List<Webinar> webinars = new ArrayList<>(webinarService.myWebinars(teacherService.findByPrincipal()));
        Assert.notEmpty(webinars);
        Webinar webinar = webinars.get(0);
        webinarService.delete(webinar);
        unauthenticate();
    }


    //An user registered as a teacher must be able to edit his/her curricula
    @Test
    public void editCurriculaOk() {
        authenticate("teacher1");
        Curricula curricula = teacherService.findByPrincipal().getCurricula();
        Assert.notNull(curricula);
        String hob = curricula.getHobbiesSection();
        String newHob = "aaaaaaaaaaaaaaaaaa";
        curricula.setHobbiesSection(newHob);
        org.junit.Assert.assertNotEquals(hob, curricula.getHobbiesSection());
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void editCurriculaNotOk() {
        authenticate(null);
        Curricula curricula = teacherService.findByPrincipal().getCurricula();
        Assert.notNull(curricula);
        String hob = curricula.getHobbiesSection();
        String newHob = "aaaaaaaaaaaaaaaaaa";
        curricula.setHobbiesSection(newHob);
        org.junit.Assert.assertNotEquals(hob, curricula.getHobbiesSection());
        unauthenticate();
    }


    //An user registered as a teacher must be able to view the created webinars and theirs assistants
    @Test
    public void myWebinarsAssitantsOk() {
        authenticate("teacher1");
        List<Webinar> webinars = new ArrayList<>(webinarService.myWebinars(teacherService.findByPrincipal()));
        Assert.notEmpty(webinars);
        Collection<User> ass = webinars.get(0).getPartakers();
        Assert.notEmpty(ass);
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void myWebinarsAssistantsNotOk() {
        authenticate(null);
        List<Webinar> webinars = new ArrayList<>(webinarService.myWebinars(teacherService.findByPrincipal()));
        Assert.notEmpty(webinars);
        Collection<User> ass = webinars.get(0).getPartakers();
        Assert.notEmpty(ass);
        unauthenticate();
    }

}