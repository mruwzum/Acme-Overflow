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
public class UserServiceTest extends AbstractTest {
   @Before
   public void setUp() {
   }

   @Test
   public void findByUserAccount() throws Exception {
   }

   @Test
   public void registerAsUser() throws Exception {
   }

   @Test
   public void registerAsTeacher() throws Exception {
   }

   @Test
   public void banUser() throws Exception {
   }

   @Test
   public void unbanUser() throws Exception {
   }

}