package domain;

import javax.persistence.*;
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


    @OneToMany(cascade = CascadeType.PERSIST)
    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    public Collection<Search> getSearches() {
        return searches;
    }

    public void setSearches(Collection<Search> searches) {
        this.searches = searches;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
