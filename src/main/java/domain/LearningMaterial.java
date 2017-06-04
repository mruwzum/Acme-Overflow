/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by daviddelatorre on 15/5/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class LearningMaterial extends DomainEntity {

    private String title;
    private String attachmentsURLs;
    private LearningMaterialType type;
    private Module module;


    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    @URL
    public String getAttachmentsURLs() {
        return attachmentsURLs;
    }

    public void setAttachmentsURLs(String attachmentsURLs) {
        this.attachmentsURLs = attachmentsURLs;
    }

    public LearningMaterialType getType() {
        return type;
    }

    public void setType(LearningMaterialType type) {
        this.type = type;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "LearningMaterial{" +
                "title='" + title + '\'' +
                '}';
    }
}
