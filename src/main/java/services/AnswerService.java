package services;


import domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.AnswerRepository;

import java.util.Collection;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class AnswerService {

    // Managed Repository ------------------------
    @Autowired
    private AnswerRepository answerRepository;


    // Supporting services -----------------------

    // Constructor -------------------------------
    public AnswerService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public Answer create() {
        Answer res;
        res = new Answer();
        return res;
    }

    public Answer findOne(int actorId) {
        Answer result;

        result = answerRepository.findOne(actorId);

        return result;
    }

    public Collection<Answer> findAll() {
        Collection<Answer> result;

        result = answerRepository.findAll();

        return result;
    }

    public Answer save(Answer actor) {
        Assert.notNull(actor);
        return answerRepository.save(actor);
    }

    public void delete(Answer actor) {
        Assert.notNull(actor);
        Assert.isTrue(answerRepository.exists(actor.getId()));
        answerRepository.delete(actor);
    }

    // Other business methods -----------------------

    public void flush(){
        answerRepository.flush();
    }


}
