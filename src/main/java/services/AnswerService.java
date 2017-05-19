package services;


import domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.AnswerRepository;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class AnswerService {

    // Managed Repository ------------------------
    @Autowired
    private AnswerRepository answerRepository;
   @Autowired
   private UserService userService;


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

      Collection<Answer> res = answerRepository.findAll();
      Assert.notNull(res);
      return res;
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

   public Boolean banAnswer(Answer answer) {

      Boolean res = false;
      if (answer.isBanned()) {
         res = false;
      } else if (!answer.isBanned()) {
         answer.setBanned(true);
         answerRepository.save(answer);
         res = true;
      }
      return res;
   }


   public Boolean unbanAnswer(Answer answer) {
      Boolean res = false;
      if (!answer.isBanned()) {
         res = true;
      } else if (answer.isBanned()) {
         answer.setBanned(false);
         answerRepository.save(answer);
         res = true;
      }
      return res;
   }

   public Collection<Answer> notBannedAnswers() {
      Collection<Answer> res = new HashSet<>();
      Collection<Answer> questions = answerRepository.findAll();
      for (Answer question : questions) {
         if (!question.isBanned()) {
            res.add(question);
         }
      }
      return res;
   }


   public Collection<Answer> myAnswers() {
      Collection<Answer> res = new HashSet<>();
      Collection<Answer> all = answerRepository.findAll();
      for (Answer q : all) {
         if (q.getOwner().equals(userService.findByPrincipal())) {
            res.add(q);
         }
      }
      return res;
   }
}
