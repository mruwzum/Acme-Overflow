package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Collection;
import java.util.Date;

/**
 * Created by daviddelatorre on 15/5/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity {


    private String title;
    private String summary;
    private Date createdDate;
    private Collection<Category> categories;
    private Collection<String> pictures;
    private Collection<Answer> answers;
    private User owner;


    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    @ElementCollection(targetClass = String.class)
    public Collection<String> getPictures() {
        return pictures;
    }

    public void setPictures(Collection<String> pictures) {
        this.pictures = pictures;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "question")
    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }
}
