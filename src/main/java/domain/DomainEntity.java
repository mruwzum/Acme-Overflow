/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package domain;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DomainEntity {

    // Constructors -----------------------------------------------------------

    private int id;


    // Identification ---------------------------------------------------------
    private int version;

    public DomainEntity() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    @Version
    public int getVersion() {
        return this.version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    // Object interface -------------------------------------------------------

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(final Object other) {
        boolean result;

        if (this == other)
            result = true;
        else if (other == null)
            result = false;
        else if (other instanceof Integer)
            result = (this.getId() == (Integer) other);
        else if (! this.getClass().isInstance(other))
            result = false;
        else
            result = (this.getId() == ((DomainEntity) other).getId());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder result;

        result = new StringBuilder();
        result.append(this.getClass().getName());
        result.append("{");
        result.append("id=");
        result.append(this.getId());
        result.append(", version=");
        result.append(this.getVersion());
        result.append("}");

        return result.toString();
    }
}
