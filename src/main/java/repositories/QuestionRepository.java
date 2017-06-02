package repositories;

import domain.Actor;
import domain.Answer;
import domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {


    @Query("select c.answers from Question c join c.answers a where c=?1 and a.banned=false")
    Collection<Answer> notBannedAnswer(Question q);


    @Modifying(clearAutomatically = true)
    @Query("update Answer set question = null where question=?1")
    int setQuestionNull(Question q);


    @Query("select a from Question q join q.answers a where q=?1 and a.owner=?2")
    Collection<Answer> myAnswerOfThisQuestion(Question q, Actor actor);
}
