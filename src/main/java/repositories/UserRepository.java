package repositories;

import domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select c from User c where c.userAccount.id = ?1")
    User findByUserAccountId(int userAccountId);


}
