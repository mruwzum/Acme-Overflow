/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package repositories;

import domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * Created by daviddelatorre on 15/5/17.
 */
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    @Query("select a from Answer a")
    Collection<Answer> all();


}
