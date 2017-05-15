package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Teacher extends Actor {

    private Curricula curricula;
    private Collection<Webinar> webinars;


    public Teacher() {
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    @NotNull
    public Curricula getCurricula() {
        return curricula;
    }

    public void setCurricula(Curricula curricula) {
        this.curricula = curricula;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    public Collection<Webinar> getWebinars() {
        return webinars;
    }

    public void setWebinars(Collection<Webinar> webinars) {
        this.webinars = webinars;
    }
}
