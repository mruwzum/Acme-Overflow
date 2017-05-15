package services;

import domain.Message;
import domain.Webinar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.WebinarRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class WebinarService {

    // Constructors--------------------------------------------------------------------------------------

    public WebinarService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private WebinarRepository webinarRepository;


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

}



