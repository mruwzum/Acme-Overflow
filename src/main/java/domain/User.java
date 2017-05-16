package domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor{

    private Collection<Question> questions;
    private Collection<Webinar> webinars;


    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "owner")
    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "partakers")
    public Collection<Webinar> getWebinars() {
        return webinars;
    }

    public void setWebinars(Collection<Webinar> webinars) {
        this.webinars = webinars;
    }
}
