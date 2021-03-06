/*
 * Copyright � 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package repositories;

import domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    @Query("select c from Actor c where c.userAccount.id = ?1")
    Administrator findByUserAccountId(int userAccountId);


    //*************************** LEVEL C ***************************


    @Query("select avg(questions.size) from User")
    Double averageNumberOfQuestionPerUser();

    @Query("select max(questions.size) from User")
    int maxNumberOfQuestionPerUser();

    @Query("select min(questions.size) from User")
    int minNumberOfQuestionPerUser();

    @Query("select avg(answers.size) from User")
    Double averageNumberOfAnswerPerUser();

    @Query("select max(answers.size) from User")
    int maxNumberOfAnswerPerUser();

    @Query("select min(answers.size) from User")
    int minNumberOfAnswerPerUser();

    @Query("select u from User u order by u.answers.size asc")
    User userWhoHaveAutoredMoreAnswer();

    @Query("select u from User u order by u.questions.size asc  ")
    Collection<User> userSortedByQuestionNumber();

    @Query("select u from User u join u.answers a order by a.likes")
    Collection<User> userSortedByNumberOfLikes();

    @Query("select u from User u join u.answers a order by a.dislikes")
    Collection<User> userSortedByNumberOfDislikes();


    //*************************** LEVEL B ***************************

    @Query("select u from User u where u.creditCard is not null")
    Collection<User> usersWithCreditCards();


    @Query("select avg(webinars.size) from Teacher ")
    Double averageNumberOfWebinarsPerTeacher();

    @Query("select t from Teacher t order by t.webinars.size")
    Collection<Teacher> teachersSortedByNumberOfWebinars();


    @Query("select w from Webinar w order by w.partakers.size")
    Collection<Webinar> webinarSortedByNumberOfAssistance();

    @Query("select avg(comments.size) from Webinar ")
    Double averageNumberOfCommentsPerWebinar();

    @Query("select max(comments.size) from Webinar ")
    int maxNumberOfCommentPerWebinar();

    @Query("select min(comments.size) from Webinar ")
    int minNumberOfCommentPerWebinar();

    @Query("select u from User u join u.questions q where q.banned = true order by u.questions.size desc")
    Collection<User> userSortedByNumberOfQuestionBanned();

    @Query("select u from User u join u.answers q where q.banned = true order by u.answers.size desc")
    Collection<User> userSortedByNumberOfAnswerBanned();

    @Query("select avg(u.questions.size) from User  u join u.questions q where q.banned = true")
    Double averageNumberOfBannedQuestionPerUser();


    //*************************** LEVEL A ***************************


    @Query("select avg(c.answers.size) from User c join c.answers a where a.banned=true")
        //ESTO DA NULL
    Double averageNumberOfBannedAnswerPerUser();


    @Query("select c from Question c where c.banned=true")
    Collection<Question> numberOfBannedQuestions();

    @Query("select c from Question c ")
    Collection<Question> numberOfNonBannedQuestions();

    @Query("select c from Answer c where c.banned=true")
    Collection<Answer> numberOfBannedAnswer();

    @Query("select c from Answer c")
    Collection<Answer> numberOfNonBannedAnswer();

    @Query("select c from User c where c.banned=true")
    Collection<User> numberOfBannedUser();

    @Query("select c from User c")
    Collection<User> numberOfNonBannedUser();


    @Query("select a from Actor a order by a.sendedMezzages.size")
    Collection<Actor> actorSortedByNumberOfSendMessage();

    @Query("select a from Actor a order by a.receivedMezzages.size")
    Collection<Actor> actorSortedByNumberOfReceivedMessages();


}
