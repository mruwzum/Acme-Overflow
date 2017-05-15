package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

/**
 * Created by daviddelatorre on 15/5/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {




    private String name;
    private String surname;
    private String phoneNumber;
    private String email;


    public Actor() {
    }



}
