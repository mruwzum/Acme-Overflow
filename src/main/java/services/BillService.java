package services;

import domain.Bill;
import domain.Duty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.BillRepository;
import repositories.DutyRepository;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class BillService {

    // Managed Repository ------------------------
    @Autowired
    private BillRepository billRepository;


    // Supporting services -----------------------

    // Constructor -------------------------------
    public BillService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public Bill create() {
        Bill res;
        res = new Bill();
        return res;
    }

    public Bill findOne(int actorId) {
        Bill result;

        result = billRepository.findOne(actorId);

        return result;
    }

    public Collection<Bill> findAll() {
        Collection<Bill> result;

        result = billRepository.findAll();

        return result;
    }

    public Bill save(Bill actor) {
        Assert.notNull(actor);
        return billRepository.save(actor);
    }

    public void delete(Bill actor) {
        Assert.notNull(actor);
        Assert.isTrue(billRepository.exists(actor.getId()));
        billRepository.delete(actor);
    }

    // Other business methods -----------------------

    public void flush() {
        billRepository.flush();
    }


}
