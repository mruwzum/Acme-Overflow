package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
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
}
