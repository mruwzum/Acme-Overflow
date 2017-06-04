/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.TeacherRepository;
import security.LoginService;
import security.UserAccount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class TeacherService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private DutyService dutyService;

    // Managed repository--------------------------------------------------------------------------------

    public TeacherService() {
        super();
    }


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


    public String totalEarn(Teacher t) {

        List<Duty> duties = new ArrayList<>(dutyService.findAll());

        Double total = teacherRepository.totalEarnWithoutComission(t);

        int duty = duties.get(0).getDutyValue();

        Double res = total * duty / 100;

        return res.toString() + " euros";
    }

    public Teacher findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        Teacher result;

        result = teacherRepository.findByUserAccountId(userAccount.getId());

        return result;
    }


    public Double averageNumberOfUserInMyWebinar(Teacher t) {

        Teacher teacher = findByPrincipal();
        Assert.notNull(teacher);

        return teacherRepository.averageNumberOfUserInMyWebinar(t);

    }

    public int maxNumberOfUserInMyWebinar(Teacher t) {

        Teacher teacher = findByPrincipal();
        Assert.notNull(teacher);

        return teacherRepository.maxNumberOfUserInMyWebinar(t);

    }

    public int minNumberOfUserInMyWebinar(Teacher t) {

        Teacher teacher = findByPrincipal();
        Assert.notNull(teacher);

        return teacherRepository.minNumberOfUserInMyWebinar(t);

    }

    public Collection<Webinar> webinarSortedByNumberOfUsers(Teacher t) {

        Teacher teacher = findByPrincipal();
        Assert.notNull(teacher);

        return teacherRepository.webinarSortedByNumberOfUsers(t);

    }

    public Collection<User> userRegisteredInMyWebinars(Teacher t) {

        Teacher teacher = findByPrincipal();
        Assert.notNull(teacher);

        return teacherRepository.userRegisteredInMyWebinars(t);

    }

    public Collection<Bill> myBills(Teacher t) {

        Teacher teacher = findByPrincipal();
        Assert.notNull(teacher);

        return teacherRepository.billsOfMyWebinars(t);

    }

}



