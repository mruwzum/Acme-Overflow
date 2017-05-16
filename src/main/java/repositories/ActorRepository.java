package repositories;

import domain.Actor;
import domain.Folder;
import domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {



    @Query("select c from Actor c where c.userAccount.id = ?1")
    Actor findByUserAccountId(int userAccountId);

    @Query("select u from User u where u.name = ?1")
    User findUserByName(String name);

    @Query("select u from Actor u where u.name = ?1")
    Actor findByName(String name);


    @Query("select u.folders from Actor u where u.id = ?1")
    Collection<Folder> getFolder(int aci);


}
