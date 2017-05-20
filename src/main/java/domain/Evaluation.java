package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Evaluation extends DomainEntity {

    Collection<EvaluationQuestion> evaluationQuestions;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "evaluation")
    @NotNull
    public Collection<EvaluationQuestion> getEvaluationQuestions() {
        return evaluationQuestions;
    }

    public void setEvaluationQuestions(Collection<EvaluationQuestion> evaluationQuestions) {
        this.evaluationQuestions = evaluationQuestions;
    }
}
