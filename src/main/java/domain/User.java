/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

    private Collection<Question> questions;
    private Collection<Webinar> webinars;
    private Collection<Bill> bills;
    private boolean banned;


    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "owner")
    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "partakers")
    public Collection<Webinar> getWebinars() {
        return webinars;
    }

    public void setWebinars(Collection<Webinar> webinars) {
        this.webinars = webinars;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public String toString() {
        return super.getName() + " " + super.getSurname();
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    public Collection<Bill> getBills() {
        return bills;
    }

    public void setBills(Collection<Bill> bills) {
        this.bills = bills;
    }
}
