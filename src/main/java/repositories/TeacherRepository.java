package repositories;

import domain.Bill;
import domain.Teacher;
import domain.User;
import domain.Webinar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("select c from Teacher c where c.userAccount.id = ?1")
    Teacher findByUserAccountId(int userAccountId);


    @Query("select avg(w.partakers.size) from Webinar w where w.owner = ?1")
    Double averageNumberOfUserInMyWebinar(Teacher t);

    @Query("select max(w.partakers.size) from Webinar w where w.owner = ?1")
    int maxNumberOfUserInMyWebinar(Teacher t);

    @Query("select min(w.partakers.size) from Webinar w where w.owner = ?1")
    int minNumberOfUserInMyWebinar(Teacher t);

    @Query("select w from Webinar w where w.owner = ?1 order by w.partakers.size")
    Collection<Webinar> webinarSortedByNumberOfUsers(Teacher t);

    @Query("select w.partakers from Webinar w where w.owner = ?1")
    Collection<User> userRegisteredInMyWebinars(Teacher t);


    @Query("select sum(b.value) from Webinar w join w.bills b where w.owner = ?1")
    Double totalEarnWithoutComission(Teacher t);

    @Query("select w.bills from Webinar w where w.owner = ?1")
    Collection<Bill> billsOfMyWebinars(Teacher t);

}
