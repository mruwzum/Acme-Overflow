package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
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

    public LearningMaterial() {
    }
}
