package services;

import domain.Moderator;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ModeratorRepository;
import repositories.UserRepository;
import security.LoginService;
import security.UserAccount;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class ModeratorService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private ModeratorRepository moderatorRepository;

    // Managed repository--------------------------------------------------------------------------------


    // Suporting services --------------------------------------------------------------------------------


    public ModeratorService() {
        super();
    }


    // Simple CRUD method --------------------------------------------------------------------------------

    public Moderator create() {
        Moderator res = new Moderator();
        return res;
    }

    public Collection<Moderator> findAll() {
        Collection<Moderator> res = moderatorRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Moderator findOne(int Moderator) {
        domain.Moderator res = moderatorRepository.findOne(Moderator);
        Assert.notNull(res);
        return res;
    }

    public Moderator save(Moderator a) {
        Assert.notNull(a);
        Moderator res = moderatorRepository.save(a);
        return res;
    }

    public void delete(Moderator a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        moderatorRepository.delete(a);

    }

    // Other business methods -------------------------------------------------------------------------------


    public User findByPrincipal() {
        User result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    public User findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        User result;

        result = moderatorRepository.findByUserAccountId(userAccount.getId());

        return result;
    }

}



