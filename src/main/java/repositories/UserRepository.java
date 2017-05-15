package repositories;

import domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface UserRepository extends JpaRepository<User, Integer> {


}
