/*
 * Copyright � 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package services;

import domain.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.EvaluationRepository;

import java.util.Collection;

@Service
@Transactional
public class EvaluationService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private EvaluationRepository evaluationRepository;

    // Managed repository--------------------------------------------------------------------------------

    public EvaluationService() {
        super();
    }


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Evaluation create() {
        Evaluation res = new Evaluation();
        return res;
    }

    public Collection<Evaluation> findAll() {
        Collection<Evaluation> res = evaluationRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Evaluation findOne(int Evaluation) {
        domain.Evaluation res = evaluationRepository.findOne(Evaluation);
        Assert.notNull(res);
        return res;
    }

    public Evaluation save(Evaluation a) {
        Assert.notNull(a);
        Evaluation res = evaluationRepository.save(a);
        return res;
    }

    public void delete(Evaluation a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        evaluationRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



