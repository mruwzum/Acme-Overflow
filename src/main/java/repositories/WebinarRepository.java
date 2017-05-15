package repositories;

import domain.Webinar;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface WebinarRepository extends JpaRepository<Webinar, Integer> {


}
