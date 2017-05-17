package repositories;

import domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

   @Query("select c from Teacher c where c.userAccount.id = ?1")
   Teacher findByUserAccountId(int userAccountId);

}
