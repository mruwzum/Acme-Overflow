/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;

/**
 * Created by mruwzum on 21/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class QuestionServiceTest extends AbstractTest {
    @Before
    public void setUp() {

    }

    @Test
    public void banQuestion() throws Exception {
    }

    @Test
    public void unbanQuestion() throws Exception {
    }

    @Test
    public void notBannedQuestions() throws Exception {
    }

    @Test
    public void myQuestions() throws Exception {
    }

    @Test
    public void notBannedAnswer() throws Exception {
    }

    @Test
    public void notBannedAnswer2() throws Exception {
    }

}