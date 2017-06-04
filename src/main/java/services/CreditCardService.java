/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package services;

import domain.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

@Service
@Transactional
public class CreditCardService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private repositories.CreditCardRepository CreditCardRepository;

    // Managed repository--------------------------------------------------------------------------------

    public CreditCardService() {
        super();
    }


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public CreditCard create() {
        CreditCard res = new CreditCard();
        return res;
    }

    public Collection<CreditCard> findAll() {
        Collection<CreditCard> res = CreditCardRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public CreditCard findOne(int CreditCard) {
        domain.CreditCard res = CreditCardRepository.findOne(CreditCard);
        Assert.notNull(res);
        return res;
    }

    public CreditCard save(CreditCard a) {
        Assert.notNull(a);
        CreditCard res = CreditCardRepository.save(a);
        return res;
    }

    public void delete(CreditCard a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        CreditCardRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



