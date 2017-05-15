package repositories;

import domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {


}
