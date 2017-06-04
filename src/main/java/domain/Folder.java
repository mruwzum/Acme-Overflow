/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

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
public class Folder extends DomainEntity {

    private String name;
    private Collection<Mezzage> mezzages;
    private Actor owner;


    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "folder")
    public Collection<Mezzage> getMezzages() {
        return mezzages;
    }

    public void setMezzages(Collection<Mezzage> mezzages) {
        this.mezzages = mezzages;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    public Actor getOwner() {
        return owner;
    }

    public void setOwner(Actor owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "name='" + name + '\'' +
                '}';
    }
}
