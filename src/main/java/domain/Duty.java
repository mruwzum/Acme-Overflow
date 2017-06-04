/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by daviddelatorre on 1/4/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Duty extends DomainEntity {


    private int dutyValue;

    @NotNull
    public int getDutyValue() {
        return dutyValue;
    }

    public void setDutyValue(int dutyValue) {
        this.dutyValue = dutyValue;
    }

    @Override
    public String toString() {
        return "Duty{" +
                "dutyValue=" + dutyValue +
                '}';
    }
}
