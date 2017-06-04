/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package services;

import domain.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ModuleRepository;

import java.util.Collection;

@Service
@Transactional
public class ModuleService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private ModuleRepository moduleRepository;

    // Managed repository--------------------------------------------------------------------------------

    public ModuleService() {
        super();
    }


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



