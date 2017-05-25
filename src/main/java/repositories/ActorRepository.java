package repositories;

import domain.Actor;
import domain.Folder;
import domain.Mezzage;
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

    @Query("select u from Actor u where u.email = ?1")
    Actor findUserByEmail(String email);

    @Query("select u from Actor u where u.name = ?1")
    Actor findByName(String name);


    @Query("select f from Actor c join c.folders f where f.name like %?2 and f.owner = ?1")
    Folder folderByName(Actor actor, String nameFolder);

    @Query("select u.folders from Actor u where u.id = ?1")
    Collection<Folder> getFolder(int aci);

    @Query("select f.mezzages from Actor u join u.folders f where u=?1")
    Collection<Mezzage> allMessages(Actor u);


}
