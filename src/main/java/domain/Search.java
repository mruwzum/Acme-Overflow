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
public class Search extends DomainEntity {

    private String keyword;
    private Category category;
    private Collection<Question> results;
    private Other owner;

    public Search() {
    }
}
