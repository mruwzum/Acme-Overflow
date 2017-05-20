package services;


import domain.Other;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.OtherRepository;
import security.LoginService;
import security.UserAccount;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class OtherService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private OtherRepository otherRepository;

    // Managed repository--------------------------------------------------------------------------------

    public OtherService() {
        super();
    }


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

    public Other findByPrincipal() {
        Other result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    public Other findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        Other result;

        result = otherRepository.findByUserAccountId(userAccount.getId());

        return result;
    }
}



