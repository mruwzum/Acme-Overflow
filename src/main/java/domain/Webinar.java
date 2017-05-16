package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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

    private Collection<Category> categories;
    private Teacher owner;
    private Collection<Comment> comments;
    private Collection<LearningMaterial> learningMaterials;
    private Collection<User> partakers;


    public Webinar() {
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    @NotNull
    @Range(min = 1, max = 10000)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @URL
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
    public Teacher getOwner() {
        return owner;
    }

    public void setOwner(Teacher owner) {
        this.owner = owner;
    }

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "webinar")
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    @NotNull
    public Collection<LearningMaterial> getLearningMaterials() {
        return learningMaterials;
    }

    public void setLearningMaterials(Collection<LearningMaterial> learningMaterials) {
        this.learningMaterials = learningMaterials;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    public Collection<User> getPartakers() {
        return partakers;
    }

    public void setPartakers(Collection<User> partakers) {
        this.partakers = partakers;
    }
}
