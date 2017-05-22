package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
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
   private Actor owner;


    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    public Collection<Question> getResults() {
        return results;
    }

    public void setResults(Collection<Question> results) {
        this.results = results;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Actor getOwner() {
        return owner;
    }

   public void setOwner(Actor owner) {
        this.owner = owner;
    }
}
