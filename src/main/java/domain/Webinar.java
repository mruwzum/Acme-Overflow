/*
 * Copyright � 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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


    private Category categories;
    private Teacher owner;
    private Collection<Comment> comments;
    private Collection<User> partakers;
    private Collection<Module> modules;
    private Collection<Bill> bills;
    private Collection<Evaluation> evaluations;
    private String URL;
    private Collection<Mezzage> webiMezzages;


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

    @Temporal(TemporalType.DATE)
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @NotNull
    public Teacher getOwner() {
        return owner;
    }

    public void setOwner(Teacher owner) {
        this.owner = owner;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "webinar")
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    public Collection<User> getPartakers() {
        return partakers;
    }

    public void setPartakers(Collection<User> partakers) {
        this.partakers = partakers;
    }


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "webinar")
    public Collection<Module> getModules() {
        return modules;
    }

    public void setModules(Collection<Module> modules) {
        this.modules = modules;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "webinar")
    public Collection<Bill> getBills() {
        return bills;
    }

    public void setBills(Collection<Bill> bills) {
        this.bills = bills;
    }


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "webinar")
    public Collection<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Collection<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    @URL
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "webinar")
    public Collection<Mezzage> getWebiMezzages() {
        return webiMezzages;
    }

    public void setWebiMezzages(Collection<Mezzage> webiMezzages) {
        this.webiMezzages = webiMezzages;
    }

    @Override
    public String toString() {
        return
                name + " - " +
                        startDate +
                        " [" + price + "] ";
    }
}
