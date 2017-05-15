package services;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.UserRepository;
import security.LoginService;
import security.UserAccount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class UserService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private UserRepository userRepository;

    // Managed repository--------------------------------------------------------------------------------


    // Suporting services --------------------------------------------------------------------------------


    public UserService() {
        super();
    }


    // Simple CRUD method --------------------------------------------------------------------------------

    public User create() {
        User res = new User();
        return res;
    }

    public Collection<User> findAll() {
        Collection<User> res = userRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public User findOne(int User) {
        domain.User res = userRepository.findOne(User);
        Assert.notNull(res);
        return res;
    }

    public User save(User a) {
        Assert.notNull(a);
        User res = userRepository.save(a);
        return res;
    }

    public void delete(User a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        userRepository.delete(a);

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

        result = userRepository.findByUserAccountId(userAccount.getId());

        return result;
    }

}



