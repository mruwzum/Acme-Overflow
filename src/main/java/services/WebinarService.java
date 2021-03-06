/*
 * Copyright � 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.WebinarRepository;

import java.util.*;

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


        Bill bill = billService.create();
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


        Date now = new Date(System.currentTimeMillis() - 100);
        Date nextMonth = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 31L);


        res = webinarRepository.listIncoming(now, nextMonth);

        Collections.sort(res, new Comparator<Webinar>() {
            public int compare(Webinar m1, Webinar m2) {
                return m2.getStartDate().toString().compareTo(m1.getStartDate().toString());
            }
        });
        return res;
    }


    public int setQuestionNull(Webinar c) {

        return webinarRepository.setQuestionNull(c);
    }

    public int setEvaluationNull(Webinar c) {

        return webinarRepository.setEvaluationNull(c);
    }

    public int setModulesNull(Webinar c) {

        return webinarRepository.setModulesNull(c);
    }

    public int setBillsNull(Webinar c) {

        return webinarRepository.setBillsNull(c);
    }

    public Boolean checkCreditCard(CreditCard creditCard) {
        Boolean res = false;
        Integer yearAct0 = (((new Date(System.currentTimeMillis())).getYear()));
        String year = "20" + yearAct0.toString().substring(1);
        Integer yearAct = new Integer(year);
        int monthAct = new Date(System.currentTimeMillis()).getMonth();
        if (creditCard == null) {
            res = false;
        } else if (creditCard.getYear() == yearAct && creditCard.getMonth() < monthAct) {
            res = false;
            creditCard.setValid(false);
        } else if (creditCard.getYear() == yearAct && creditCard.getMonth() >= monthAct) {
            res = true;
            creditCard.setValid(true);
        } else if (creditCard.getYear() >= yearAct) {
            res = true;
            creditCard.setValid(true);
        } else if (creditCard.getYear() < yearAct) {
            res = false;
            creditCard.setValid(false);
        }
        return res;
    }
}



