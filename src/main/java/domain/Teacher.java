/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Teacher extends Actor {

    private Curricula curricula;
    private Collection<Webinar> webinars;
    private String IBAN;


    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    public Curricula getCurricula() {
        return curricula;
    }

    public void setCurricula(Curricula curricula) {
        this.curricula = curricula;
    }

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "owner")
    public Collection<Webinar> getWebinars() {
        return webinars;
    }

    public void setWebinars(Collection<Webinar> webinars) {
        this.webinars = webinars;
    }

    @Pattern(regexp = "[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}")
    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    @Override
    public String toString() {
        return super.getName() + " " + super.getSurname();
    }
}
