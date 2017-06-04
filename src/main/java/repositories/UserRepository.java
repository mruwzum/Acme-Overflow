/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package repositories;

import domain.Mezzage;
import domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select c from User c where c.userAccount.id = ?1")
    User findByUserAccountId(int userAccountId);


    @Query("select m from User u join u.webinars w join w.webiMezzages m where u=?1")
    Collection<Mezzage> myWebbinarMezzages(User u);


    @Query("select c from User c join c.userAccount u join u.authorities a where a.authority = 'USER' and c.banned = false ")
    Collection<User> usersNotBanned();

    @Query("select c from User c join c.userAccount u join u.authorities a where a.authority = 'MODERATOR'")
    Collection<User> usersNotModerator();

}
