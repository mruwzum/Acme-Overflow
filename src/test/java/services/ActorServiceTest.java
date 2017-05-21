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
public class ActorServiceTest extends AbstractTest {
   @Before
   public void setUp() {
   }

   @Test
   public void createNewFolder() throws Exception {
   }

   @Test
   public void createFolder() throws Exception {
   }

   @Test
   public void findByName() throws Exception {
   }

   @Test
   public void getFolders() throws Exception {
   }

   @Test
   public void modifyFolder() throws Exception {
   }

   @Test
   public void deleteFolder() throws Exception {
   }

   @Test
   public void recieveMessage() throws Exception {
   }

   @Test
   public void textMessage() throws Exception {
   }

}