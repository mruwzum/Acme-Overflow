package repositories;

import domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface AnswerRepository extends JpaRepository<Answer, Integer> {


}
