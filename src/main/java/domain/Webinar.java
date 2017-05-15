package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

/**
 * Created by daviddelatorre on 15/5/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Webinar extends DomainEntity {


    private String name;
    private String description;
    private Date startDate;
    private double price;
    private String picture;

    private Category category;
    private Teacher owner;
    private Collection<Comment> comments;
    private Collection<LearningMaterial> learningMaterials;
    private Collection<User> partakers;


    public Webinar() {
    }


}
