package repositories;

import domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {


}
