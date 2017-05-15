package services;

import domain.Message;
import domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.QuestionRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class QuestionService {

    // Constructors--------------------------------------------------------------------------------------

    public QuestionService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private QuestionRepository questionRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Question create() {
        Question res = new Question();
        return res;
    }

    public Collection<Question> findAll() {

        Collection<Question> res = questionRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Question findOne(int Message) {
        Question res = questionRepository.findOne(Message);
        Assert.notNull(res);
        return res;
    }

    public Question save(Question a) {
        Assert.notNull(a);
        Question res = questionRepository.save(a);
        return res;
    }

    public void delete(Question a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        questionRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



