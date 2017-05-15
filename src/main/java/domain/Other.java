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
public class Other extends Actor {


    private Collection<Answer> answers;
    private Collection<Search> searches;
    private Collection<Comment> comments ;
    private CreditCard creditCard;


    public Other() {
    }
}
