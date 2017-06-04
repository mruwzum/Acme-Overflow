/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package domain;

import org.hibernate.validator.constraints.URL;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Banner extends DomainEntity {


    public String url;

    // Relationships ---------------------------------------------------------


    @NotNull
    @URL
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
