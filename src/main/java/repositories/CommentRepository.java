/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package repositories;

import domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by david on 05/11/2016.
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {


}
