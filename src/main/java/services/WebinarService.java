package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.WebinarRepository;

import java.util.*;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class WebinarService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private WebinarRepository webinarRepository;
    @Autowired
    private BillService billService;

    // Managed repository--------------------------------------------------------------------------------

    public WebinarService() {
        super();
    }


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Webinar create() {
        Webinar res = new Webinar();
        return res;
    }

    public Collection<Webinar> findAll() {

        Collection<Webinar> res = webinarRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Webinar findOne(int Message) {
        domain.Webinar res = webinarRepository.findOne(Message);
        Assert.notNull(res);
        return res;
    }

    public Webinar save(Webinar a) {
        Assert.notNull(a);
        Webinar res = webinarRepository.save(a);
        return res;
    }

    public void delete(Webinar a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        webinarRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------


    public void flush() {
        webinarRepository.flush();
    }


    public boolean register(User user, Webinar webinar) {
        boolean res = true;
        if (webinar.getPartakers().contains(user)) {
            res = false;
        }


        Bill bill =  billService.create();
        bill.setOwner(user);
        bill.setWebinar(webinar);
        bill.setValue(webinar.getPrice());
        bill.setNumber(getSaltString());
        billService.save(bill);

        user.getWebinars().add(webinar);
        webinar.getPartakers().add(user);
        return res;
    }

    public boolean unregister(User user, Webinar webinar) {
        boolean res = true;

        if (! webinar.getPartakers().contains(user)) {
            res = false;
        }
        webinar.getPartakers().remove(user);
        user.getWebinars().remove(webinar);
        return res;

    }


    public Collection<Webinar> myWebinars(Teacher t) {

        Assert.notNull(t);
        return webinarRepository.myWebinars(t);
    }

   public Collection<Webinar> myWebinarsO(Actor t) {

        Assert.notNull(t);
        return webinarRepository.myWebinarso(t);
    }


    public Collection<Webinar> webinarsToGo(User u) {

        Assert.notNull(u);
        return webinarRepository.webinarsToAssist(u);

    }


    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

   public Collection<Webinar> listIncoming() {
      Collection<Webinar> webinars;
      List<Webinar> res = new ArrayList<>();
      webinars = webinarRepository.findAll();

      for (Webinar webinar : webinars) {
         if (webinar.getStartDate().after(new Date(System.currentTimeMillis() - 30 * 24 * 60L)) && webinar.getStartDate().getYear() == 117) {
            res.add(webinar);
         }
//TODO query
      }
      Collections.sort(res, new Comparator<Webinar>() {
         public int compare(Webinar m1, Webinar m2) {
            return m2.getStartDate().toString().compareTo(m1.getStartDate().toString());
         }
      });
      return res;
   }

}



