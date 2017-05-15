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
public class Curricula extends DomainEntity {

    private String photo;
    private String educationSection;
    private String experienceSection;
    private Collection<String> referencias;
    private String hobbiesSection;

    public Curricula() {
    }
}
