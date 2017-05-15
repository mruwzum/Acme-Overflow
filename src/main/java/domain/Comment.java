package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by daviddelatorre on 15/5/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {


    private String title;
    private String text;
    private Date creationDate;
    private Other owner;

    public Comment() {
    }
}
