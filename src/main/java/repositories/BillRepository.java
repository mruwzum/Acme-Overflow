package repositories;

import domain.Bill;
import domain.Duty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
}