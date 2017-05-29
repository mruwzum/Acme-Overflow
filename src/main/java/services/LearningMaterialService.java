package services;

import domain.LearningMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class LearningMaterialService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private repositories.LearningMaterialRepository learningMaterialRepository;

    // Managed repository--------------------------------------------------------------------------------

    public LearningMaterialService() {
        super();
    }


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public LearningMaterial create() {
        LearningMaterial res = new LearningMaterial();
        return res;
    }

    public Collection<LearningMaterial> findAll() {
       Collection<LearningMaterial> res = learningMaterialRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public LearningMaterial findOne(int LearningMaterial) {
       domain.LearningMaterial res = learningMaterialRepository.findOne(LearningMaterial);
        Assert.notNull(res);
        return res;
    }

    public LearningMaterial save(LearningMaterial a) {
        Assert.notNull(a);
       LearningMaterial res = learningMaterialRepository.save(a);
        return res;
    }

    public void delete(LearningMaterial a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
       learningMaterialRepository.delete(a);

    }

    // Other business methods -------------------------------------------------------------------------------

}



