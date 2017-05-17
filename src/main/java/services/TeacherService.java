package services;

import domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.TeacherRepository;
import security.LoginService;
import security.UserAccount;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class TeacherService {

    // Constructors--------------------------------------------------------------------------------------

    public TeacherService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private TeacherRepository teacherRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Teacher create() {
        Teacher res = new Teacher();
        return res;
    }

    public Collection<Teacher> findAll() {

        Collection<Teacher> res = teacherRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Teacher findOne(int Message) {
        domain.Teacher res = teacherRepository.findOne(Message);
        Assert.notNull(res);
        return res;
    }

    public Teacher save(Teacher a) {
        Assert.notNull(a);
        Teacher res = teacherRepository.save(a);
        return res;
    }

    public void delete(Teacher a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        teacherRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

   public Teacher findByPrincipal() {
      Teacher result;
      UserAccount userAccount;

      userAccount = LoginService.getPrincipal();
      Assert.notNull(userAccount);
      result = findByUserAccount(userAccount);
      Assert.notNull(result);

      return result;
   }

   public Teacher findByUserAccount(UserAccount userAccount) {
      Assert.notNull(userAccount);

      Teacher result;

      result = teacherRepository.findByUserAccountId(userAccount.getId());

      return result;
   }
}



