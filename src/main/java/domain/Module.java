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
public class Module extends DomainEntity {


    private String title;
    private String description;
    private Collection<LearningMaterial> learningMaterials;
    private Webinar webinar;


    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    public Collection<LearningMaterial> getLearningMaterials() {
        return learningMaterials;
    }

    public void setLearningMaterials(Collection<LearningMaterial> learningMaterials) {
        this.learningMaterials = learningMaterials;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    public Webinar getWebinar() {
        return webinar;
    }

    public void setWebinar(Webinar webinar) {
        this.webinar = webinar;
    }

    @Override
    public String toString() {
        return "Module{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
