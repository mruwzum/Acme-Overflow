package services;


import domain.Other;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.OtherRepository;
import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class OtherService {

    // Constructors--------------------------------------------------------------------------------------

    public OtherService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private OtherRepository otherRepository;



    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------


    public Collection<Other> findAll() {

        Collection<Other> res = otherRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Other findOne(int Others) {
        Other res = otherRepository.findOne(Others);
        Assert.notNull(res);
        return res;
    }

    public Other save(Other a) {
        Assert.notNull(a);
        Other res = otherRepository.save(a);
        return res;
    }

    public void delete(Other a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        otherRepository.delete(a);

    }

    // Other business methods -------------------------------------------------------------------------------


}



