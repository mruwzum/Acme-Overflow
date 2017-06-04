/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package domain;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by daviddelatorre on 15/5/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class EvaluationQuestion extends DomainEntity {


    private String title;
    private String answer;
    private Evaluation evaluation;


    @NotNull
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        return "EvaluationQuestion{" +
                "title='" + title + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
