package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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
}
