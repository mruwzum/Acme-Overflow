package repositories;

import domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface ModuleRepository extends JpaRepository<Module, Integer> {


}
