package repositories;

import domain.Actor;
import domain.Teacher;
import domain.User;
import domain.Webinar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface WebinarRepository extends JpaRepository<Webinar, Integer> {


    @Query("select c from Webinar c where c.owner = ?1")
    Collection<Webinar> myWebinars(Teacher t);

    @Query("select c from Webinar c where c.owner = ?1")
    Collection<Webinar> myWebinarso(Actor t);

    @Query("select u.webinars from User u where u=?1")
    Collection<Webinar> webinarsToAssist(User u);

    @Modifying(clearAutomatically = true)
    @Query("update Comment set webinar =  null where webinar=?1")
    int setQuestionNull(Webinar q);

    @Modifying(clearAutomatically = true)
    @Query("update Evaluation set webinar = null where webinar=?1")
    int setEvaluationNull(Webinar q);


    @Modifying(clearAutomatically = true)
    @Query("update Module set webinar =  null where webinar=?1")
    int setModulesNull(Webinar q);

    @Modifying(clearAutomatically = true)
    @Query("update Bill set webinar =  null where webinar=?1")
    int setBillsNull(Webinar q);

}
