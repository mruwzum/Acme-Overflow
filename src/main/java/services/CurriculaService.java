package services;

import domain.Curricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class CurriculaService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private repositories.CurriculaRepository CurriculaRepository;

    // Managed repository--------------------------------------------------------------------------------

    public CurriculaService() {
        super();
    }


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Curricula create() {
        Curricula res = new Curricula();
        return res;
    }

    public Collection<Curricula> findAll() {
        Collection<Curricula> res = CurriculaRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Curricula findOne(int Curricula) {
        domain.Curricula res = CurriculaRepository.findOne(Curricula);
        Assert.notNull(res);
        return res;
    }

    public Curricula save(Curricula a) {

        Assert.notNull(a);
        Curricula res = CurriculaRepository.save(a);
        return res;
    }

    public void delete(Curricula a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        CurriculaRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



