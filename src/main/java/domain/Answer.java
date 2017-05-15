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
public class Answer extends DomainEntity {

    private  String title;
    private String description;
    private Collection<String> pictures;
    private int likes;
    private int dislikes;
    private Other owner;

    public Answer() {
    }
}
