package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
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


    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //TODO poner esto como clase aparte?

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
}
