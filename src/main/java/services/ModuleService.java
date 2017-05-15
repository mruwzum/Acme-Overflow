package services;

import domain.Message;
import domain.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ModuleRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class ModuleService {

    // Constructors--------------------------------------------------------------------------------------

    public ModuleService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private ModuleRepository moduleRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Module create() {
        Module res = new Module();
        return res;
    }

    public Collection<Module> findAll() {

        Collection<Module> res = moduleRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Module findOne(int Message) {
        Module res = moduleRepository.findOne(Message);
        Assert.notNull(res);
        return res;
    }

    public Module save(Module a) {
        Assert.notNull(a);
        Module res = moduleRepository.save(a);
        return res;
    }

    public void delete(Module a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        moduleRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



