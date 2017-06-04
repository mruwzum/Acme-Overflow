/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import services.*;
import utilities.AbstractTest;

import javax.transaction.Transactional;

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


}
