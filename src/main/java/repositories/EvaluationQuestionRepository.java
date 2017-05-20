package repositories;

import domain.EvaluationQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface EvaluationQuestionRepository extends JpaRepository<EvaluationQuestion, Integer> {


}
