package repositories;

import domain.Other;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface OtherRepository extends JpaRepository<Other, Integer> {


    @Query("select c from Other c where c.userAccount.id = ?1")
    Other findByUserAccountId(int userAccountId);
}
