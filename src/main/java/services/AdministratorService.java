/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class AdministratorService {

    // Managed Repository ------------------------
    @Autowired
    private AdministratorRepository administratorRepository;

    // Supporting services -----------------------


    public AdministratorService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public Administrator create() {
        Administrator res;
        res = new Administrator();
        return res;
    }

    public Administrator findOne(int actorId) {
        Administrator result;

        result = administratorRepository.findOne(actorId);

        return result;
    }

    public Collection<Administrator> findAll() {
        Collection<Administrator> result;

        result = administratorRepository.findAll();

        return result;
    }

    public Administrator save(Administrator actor) {
        Assert.notNull(actor);
        return administratorRepository.save(actor);
    }

    public void delete(Administrator actor) {
        Assert.notNull(actor);
        Assert.isTrue(administratorRepository.exists(actor.getId()));
        administratorRepository.delete(actor);
    }


    // Other business methods -----------------------


    public Administrator findByPrincipal() {
        Administrator result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    private Administrator findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        Administrator result;

        result = administratorRepository.findByUserAccountId(userAccount.getId());

        return result;
    }


    public Double averageNumberOfQuestionPerUser() {


        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.averageNumberOfQuestionPerUser();
    }

    public int maxNumberOfQuestionPerUser() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.maxNumberOfQuestionPerUser();
    }

    public int minNumberOfQuestionPerUser() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.minNumberOfQuestionPerUser();
    }

    public Double averageNumberOfAnswerPerUser() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.averageNumberOfAnswerPerUser();
    }


    public int maxNumberOfAnswerPerUser() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.maxNumberOfAnswerPerUser();
    }

    public int minNumberOfAnswerPerUser() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.minNumberOfAnswerPerUser();
    }


    public Collection<User> userSortedByQuestionNumber() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.userSortedByQuestionNumber();
    }

    public Collection<User> userSortedByNumberOfLikes() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.userSortedByNumberOfLikes();
    }

    public Collection<User> usersWithCreditCards() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.usersWithCreditCards();
    }

    public Double averageNumberOfWebinarsPerTeacher() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.averageNumberOfWebinarsPerTeacher();
    }

    public Collection<Teacher> teachersSortedByNumberOfWebinars() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.teachersSortedByNumberOfWebinars();
    }

    public Collection<Webinar> webinarSortedByNumberOfAssistance() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.webinarSortedByNumberOfAssistance();
    }

    public Double averageNumberOfCommentsPerWebinar() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.averageNumberOfCommentsPerWebinar();
    }

    public int maxNumberOfCommentPerWebinar() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.maxNumberOfCommentPerWebinar();
    }

    public int minNumberOfCommentPerWebinar() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.minNumberOfCommentPerWebinar();
    }

    public Collection<User> userSortedByNumberOfQuestionBanned() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.userSortedByNumberOfQuestionBanned();
    }

    public Collection<User> userSortedByNumberOfAnswerBanned() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.userSortedByNumberOfAnswerBanned();
    }

    public Double averageNumberOfBannedQuestionPerUser() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.averageNumberOfBannedQuestionPerUser();
    }

    public Double averageNumberOfBannedAnswerPerUser() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.averageNumberOfBannedAnswerPerUser();
    }

    //Question banned percentaje

    public int numberOfBannedQuestions() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.numberOfBannedQuestions().size();
    }

    public int numberOfNonBannedQuestions() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.numberOfNonBannedQuestions().size();
    }


    public String percentageOfBannedQuestion() {


        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        int banned = this.numberOfBannedQuestions();
        int not = this.numberOfNonBannedQuestions();


        double total = not / banned;

        return total + "%";

    }

    //Answer banned percentaje


    public int numberOfBannedAnswer() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.numberOfBannedAnswer().size();
    }

    public int numberOfNonBannedAnswer() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.numberOfNonBannedAnswer().size();
    }

    public String percentageofBannedAnswer() {


        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        int banned = this.numberOfBannedAnswer();
        int not = this.numberOfNonBannedAnswer();


        double total = not / banned;

        return total + "%";

    }

    //User banned percentaje

    public int numberOfBannedUser() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.numberOfBannedUser().size();
    }

    public int numberOfNonBannedUser() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.numberOfNonBannedUser().size();
    }

    public String percentageofBannedUser() {


        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        int banned = this.numberOfBannedUser();
        int not = this.numberOfNonBannedUser();


        double total = not / banned;

        return total + "%";

    }


    public Collection<Actor> actorSortedByNumberOfSendMessage() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.actorSortedByNumberOfSendMessage();
    }

    public Collection<Actor> actorSortedByNumberOfReceivedMessages() {

        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        return administratorRepository.actorSortedByNumberOfReceivedMessages();
    }


    public void flush() {
        administratorRepository.flush();
    }


    //DASHBOARD


}
