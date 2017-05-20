package services;

import domain.Mezzage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.MezzageRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class MezzageService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private MezzageRepository MezzageRepository;

    // Managed repository--------------------------------------------------------------------------------

   public MezzageService() {
        super();
    }


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

   public Mezzage create() {
      Mezzage res = new Mezzage();
        return res;
    }

   public Collection<Mezzage> findAll() {

      Collection<Mezzage> res = MezzageRepository.findAll();
        Assert.notNull(res);
        return res;
    }

   public Mezzage findOne(int mezzageId) {
      Mezzage res = MezzageRepository.findOne(mezzageId);
        Assert.notNull(res);
        return res;
    }

   public Mezzage save(Mezzage a) {
        Assert.notNull(a);
      Mezzage res = MezzageRepository.save(a);
        return res;
    }

   public void delete(Mezzage a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
      MezzageRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



