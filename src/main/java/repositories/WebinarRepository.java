package repositories;

import domain.Other;
import domain.Teacher;
import domain.User;
import domain.Webinar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface WebinarRepository extends JpaRepository<Webinar, Integer> {


    @Query("select c from Webinar c where c.owner = ?1")
    Collection<Webinar> myWebinars(Teacher t);

    @Query("select c from Webinar c where c.owner = ?1")
    Collection<Webinar> myWebinarso(Other t);

    @Query("select u.webinars from User u where u=?1")
    Collection<Webinar> webinarsToAssist(User u);

}
