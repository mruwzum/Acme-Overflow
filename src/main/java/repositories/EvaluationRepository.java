package repositories;

import domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {


}
