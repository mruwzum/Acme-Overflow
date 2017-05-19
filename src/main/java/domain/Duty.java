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
}
