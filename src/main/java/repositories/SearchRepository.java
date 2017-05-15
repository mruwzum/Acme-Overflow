package repositories;

import domain.Search;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface SearchRepository extends JpaRepository<Search, Integer> {


}
