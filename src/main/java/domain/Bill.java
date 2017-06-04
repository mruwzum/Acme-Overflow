/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by daviddelatorre on 1/4/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Bill extends DomainEntity {


    String number;
    Double value;

    User owner;
    Webinar webinar;

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @NotNull
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    public Webinar getWebinar() {
        return webinar;
    }

    public void setWebinar(Webinar webinar) {
        this.webinar = webinar;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "number='" + number + '\'' +
                ", value=" + value +
                '}';
    }
}
