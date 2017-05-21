package services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;

import static org.junit.Assert.*;

/**
 * Created by mruwzum on 21/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class WebinarServiceTest extends AbstractTest {
   @Before
   public void setUp() {
   }

   @Test
   public void register() throws Exception {
   }

   @Test
   public void unregister() throws Exception {
   }

   @Test
   public void myWebinars() throws Exception {
   }

   @Test
   public void myWebinarsO() throws Exception {
   }

   @Test
   public void webinarsToGo() throws Exception {
   }

}