package repositories;

import domain.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by daviddelatorre on 17/5/17.
 */
public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {

    @Query("select c from Moderator c where c.userAccount.id = ?1")
    Moderator findByUserAccountId(int userAccountId);
}
