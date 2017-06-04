/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Answer extends DomainEntity {

    private String title;
    private String description;
    private Collection<String> pictures;
    private int likes;
    private int dislikes;
    private Question question;
    private boolean banned;
    private Actor owner;
    private boolean isTeacher;
    private Collection<Actor> likedActors;

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
    @Column(length = 10000)
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

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Actor getOwner() {
        return owner;
    }

    public void setOwner(Actor owner) {
        this.owner = owner;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Collection<Actor> getLikedActors() {
        return likedActors;
    }

    public void setLikedActors(Collection<Actor> likedActors) {
        this.likedActors = likedActors;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
