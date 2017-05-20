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

    @Query("select u from User u join u.questions q where q.banned = true order by u.questions.size")
    Collection<User> userSortedByNumberOfQuestionBanned();

    @Query("select u from User u join u.answers q where q.banned = true order by u.answers.size")
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


    //A listing with the number of chorbies per country and city.
//    @Query("select c from Chorbi c group by c.coordinate.city")
//    Collection<Chorbi> chorbiesPerCity();
//    @Query("select c from Chorbi c group by c.coordinate.country")
//    Collection<Chorbi> chorbiesPerCountry();
//
//    //The minimum, the maximum, and the average ages of the chorbies.
//    @Query("select avg(age) from Chorbi")
//    Double averageAgesOfTheChorbies();
//    @Query("select max(age) from Chorbi")
//    Integer maxAgeOfTheChorbies();
//    @Query("select min(age) from Chorbi")
//    Integer minAgeOfTheChorbies();
//
//
//
//    @Query("select c from Chorbi c")
//    Collection<Chorbi> allChorbi();
//
//    //The ratio of chorbies who have not registered a credit card or have regis- tered an invalid credit card
//    @Query("select c from Chorbi c where c.creditCard.valid=false")
//    Collection<Chorbi> chorbiWithInvalidCreditCard();
//    @Query("select c from Chorbi c where  c.creditCard is null ")
//    Collection<Chorbi> chorbiWithoutCreditCard();
//
//
////    //The ratios of chorbies who search for ?activities?, ?friendship?, and ?love?.
//
//    @Query("select c from Chorbi c where c.relationship=0")
//    Collection<Chorbi> chorbiWhoSearchActivities();
//    @Query("select c from Chorbi c where c.relationship=1")
//    Collection<Chorbi> chorbiWhoSearchFriendShip();
//    @Query("select c from Chorbi c where c.relationship=2")
//    Collection<Chorbi> chorbiWhoSearchLove();
//
//
//    //The list of chorbies, sorted by the number of likes they have got
//
//    @Query("select c from Chorbi c order by c.likes.size desc")
//    Collection<Chorbi> chorbiesSortedByTheNumberOfLikes();
//
//    //The minimum, the maximum, and the average number of likes per chorbi
//
//    @Query("select avg(likes.size) from Chorbi")
//    Double averageNumberOfLikesPerChorbi();
//    @Query("select max(likes.size) from Chorbi")
//    Integer maxNumberOfLikePerChorbi();
//    @Query("select min(likes.size) from Chorbi")
//    Integer minNumberOfLikePerChorbi();
//
//    //The minimum, the maximum, and the average number of chirps that a chor- bi receives from other chorbies
//
//    @Query("select avg(chirps.size) from Chorbi")
//    Double averageNumberOfChirpsReceived();
//    @Query("select max(chirps.size) from Chorbi")
//    Integer maxNumberOfChirpsReceived();
//    @Query("select min(chirps.size) from Chorbi")
//    Integer minNumberOfChirpsReceived();
//
//
//    //The minimum, the maximum, and the average number of chirps that a chor- bi sends to other chorbies
//
//    @Query("select avg(myChirps.size) from Chorbi")
//    Double averageNumberOfChirpsSended();
//    @Query("select max(myChirps.size) from Chorbi")
//    Integer maxNumberOfChirpsSended();
//    @Query("select min(myChirps.size) from Chorbi")
//    Integer minNumberOfChirpsSended();
//
//
//    //The chorbies who have got more chirps
//    @Query("select c from Chorbi c order by c.chirps.size desc")
//    Collection<Chorbi> chorbieWhoHaveMoreChirpsReceived();
//
//    //The chorbies who have sent more chirps
//    @Query("select c from Chorbi c order by c.myChirps.size desc")
//    Collection<Chorbi> chorbieWhoHaveMoreChirpsSended();
//
//
//    //************************************************************************** ACME CHORBIES 2.0 **************************************************************************
//
//
//    @Query("select c from Manager c order by c.createdEvents.size asc")
//    Collection<Manager> managerOrganizedByNumberOfEvents();
//
//    @Query("select c from Chorbi c order by c.eventsToGo.size asc ")
//    Collection<Chorbi> chorbiSortedByNumberOfEventsToGo();
//
//
//
//
//    @Query("select avg(numberOfStars) from Chorbi")
//    Double averageNumberOfStarsPerChorbi();
//
//    @Query("select max(numberOfStars) from Chorbi")
//    int maxNumberOfStarsPerChorbi();
//
//    @Query("select min(numberOfStars) from Chorbi")
//    int minNumberOfStarsPerChorbi();
//
//    @Query("select c from Chorbi c order by avg(c.numberOfStars) asc ")
//    Collection<Chorbi> chorbiesSortedByAverageOfLikes();
//

}
