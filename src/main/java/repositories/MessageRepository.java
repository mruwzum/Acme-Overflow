package repositories;

import domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {


}
