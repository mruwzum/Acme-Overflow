/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Curricula extends DomainEntity {

    private String photo;
    private String educationSection;
    private String experienceSection;
    private Collection<String> referencias;
    private String hobbiesSection;
    private boolean isApprobed;
    private Teacher owner;


    @NotBlank
    @URL
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getEducationSection() {
        return educationSection;
    }

    public void setEducationSection(String educationSection) {
        this.educationSection = educationSection;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getExperienceSection() {
        return experienceSection;
    }

    public void setExperienceSection(String experienceSection) {
        this.experienceSection = experienceSection;
    }

    @ElementCollection(targetClass = String.class)
    public Collection<String> getReferencias() {
        return referencias;
    }

    public void setReferencias(Collection<String> referencias) {
        this.referencias = referencias;
    }


    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getHobbiesSection() {
        return hobbiesSection;
    }

    public void setHobbiesSection(String hobbiesSection) {
        this.hobbiesSection = hobbiesSection;
    }

    public boolean isApprobed() {
        return isApprobed;
    }

    public void setApprobed(boolean approbed) {
        isApprobed = approbed;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Teacher getOwner() {
        return owner;
    }

    public void setOwner(Teacher owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Curricula{" +
                "photo='" + photo + '\'' +
                ", educationSection='" + educationSection + '\'' +
                ", experienceSection='" + experienceSection + '\'' +
                ", hobbiesSection='" + hobbiesSection + '\'' +
                '}';
    }
}
