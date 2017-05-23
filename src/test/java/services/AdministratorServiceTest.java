package services;

import domain.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mruwzum on 21/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class AdministratorServiceTest extends AbstractTest {
   @Autowired
   private ActorService actorService;
   @Autowired
   private UserService userService;
   @Autowired
   private CategoryService categoryService;
   @Autowired
   private AdministratorService administratorService;



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
      category.setSons(categories);
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
   public void test() {

   }

}