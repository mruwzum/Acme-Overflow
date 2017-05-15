package repositories;

import domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {


}
