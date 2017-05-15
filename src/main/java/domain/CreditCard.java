package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

/**
 * Created by daviddelatorre on 15/5/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity {

    private String holderName;
    private String number;
    private int year;
    private int month;
    private String CVV;
    private CreditCardType type;
    private Other owner;

    public CreditCard() {
    }
}
