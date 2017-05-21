package services;

import domain.Banner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import security.UserAccountService;
import utilities.AbstractTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by mruwzum on 21/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class BannerServiceTest extends AbstractTest {
   @Autowired
   private BannerService bannerService;
   @Autowired
   private ActorService actorService;
   @Autowired
   private UserService userService;
   @Autowired
   private UserAccountService userAccountService;


   @Before
   public void setUp() {

      Banner banner = new Banner();
      banner.setUrl("https://about.canva.com/wp-content/uploads/sites/3/2017/02/congratulations_-banner.png");
      bannerService.save(banner);
      Banner banner2 = new Banner();
      banner2.setUrl("https://about.canva.com/wp-content/uploads/sites/3/2017/02/congratulations_-banner.png");
      bannerService.save(banner2);
      Banner banner3 = new Banner();
      banner3.setUrl("https://about.canva.com/wp-content/uploads/sites/3/2017/02/congratulations_-banner.png");
      bannerService.save(banner3);


   }

   //See a wellcome page banner that advertises lasts projects from the holding company. Positive&Negative tests
   @Test
   public void testGetBannerOk() {
      authenticate(null);

      Set<Banner> bannerSet = new HashSet<>();
      bannerSet.addAll(bannerService.findAll());
      Assert.notEmpty(bannerSet);

      unauthenticate();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testGetBannerNotOk() {
      authenticate(null);

      Set<Banner> bannerSet = new HashSet<>();
      bannerSet.addAll(bannerService.findAll());
      bannerSet.removeAll(bannerService.findAll());
      Assert.notEmpty(bannerSet);

      unauthenticate();
   }

}