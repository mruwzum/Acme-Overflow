/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

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

    private Collection<EvaluationQuestion> evaluationQuestions;
    private Webinar webinar;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluation")
    @NotNull
    public Collection<EvaluationQuestion> getEvaluationQuestions() {
        return evaluationQuestions;
    }

    public void setEvaluationQuestions(Collection<EvaluationQuestion> evaluationQuestions) {
        this.evaluationQuestions = evaluationQuestions;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Webinar getWebinar() {
        return webinar;
    }

    public void setWebinar(Webinar webinar) {
        this.webinar = webinar;
    }
}
