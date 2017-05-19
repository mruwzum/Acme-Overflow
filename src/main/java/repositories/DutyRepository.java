package repositories;

import domain.Duty;
import domain.SearchCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface DutyRepository extends JpaRepository<Duty, Integer> {
}
