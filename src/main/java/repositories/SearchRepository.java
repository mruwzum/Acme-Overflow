package repositories;

import domain.Category;
import domain.Question;
import domain.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface SearchRepository extends JpaRepository<Search, Integer> {



    @Query("select c from Question c where c.title like %?1 or c.summary like %?1")
    Collection<Question> questionsByKeyword(String keyword);


    @Query("select c from Question c where c.title like %?1 or c.summary like %?1 and c.categories = ?2")
    Collection<Question> questionsByKeywordAndCategory(String keyword, Category category);

}
