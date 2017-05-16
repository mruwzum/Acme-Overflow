package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class LearningMaterial extends DomainEntity {

    private String title;
    private Collection<String> attachmentsURLs;
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
    @ElementCollection(targetClass = String.class)
    @NotNull
    public Collection<String> getAttachmentsURLs() {
        return attachmentsURLs;
    }

    public void setAttachmentsURLs(Collection<String> attachmentsURLs) {
        this.attachmentsURLs = attachmentsURLs;
    }

    public LearningMaterialType getType() {
        return type;
    }

    public void setType(LearningMaterialType type) {
        this.type = type;
    }
}
