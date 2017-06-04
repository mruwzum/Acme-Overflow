/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package services;

import domain.Duty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.DutyRepository;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class DutyService {

    // Managed Repository ------------------------
    @Autowired
    private DutyRepository dutyRepository;


    // Supporting services -----------------------

    // Constructor -------------------------------
    public DutyService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public Duty create() {
        Duty res;
        res = new Duty();
        return res;
    }

    public Duty findOne(int actorId) {
        Duty result;

        result = dutyRepository.findOne(actorId);

        return result;
    }

    public Collection<Duty> findAll() {
        Collection<Duty> result;

        result = dutyRepository.findAll();

        return result;
    }

    public Duty save(Duty actor) {
        Assert.notNull(actor);
        return dutyRepository.save(actor);
    }

    public void delete(Duty actor) {
        Assert.notNull(actor);
        Assert.isTrue(dutyRepository.exists(actor.getId()));
        dutyRepository.delete(actor);
    }

    // Other business methods -----------------------

    public void flush() {
        dutyRepository.flush();
    }


}
