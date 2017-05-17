package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private Question question;


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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ElementCollection(targetClass = String.class)
    public Collection<String> getPictures() {
        return pictures;
    }

    public void setPictures(Collection<String> pictures) {
        this.pictures = pictures;
    }

    @Range(min = 0, max = 9999)
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Range(min = 0, max = 9999)
    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }



    @ManyToOne(cascade = CascadeType.PERSIST)
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
