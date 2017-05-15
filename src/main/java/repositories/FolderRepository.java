package repositories;

import domain.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface FolderRepository extends JpaRepository<Folder, Integer> {


}
