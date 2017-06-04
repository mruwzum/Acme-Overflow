/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package services;

import domain.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

@Service
@Transactional
public class FolderService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private repositories.FolderRepository FolderRepository;

    // Managed repository--------------------------------------------------------------------------------

    public FolderService() {
        super();
    }


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Folder create() {
        Folder res = new Folder();
        return res;
    }

    public Collection<Folder> findAll() {
        Collection<Folder> res = FolderRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Folder findOne(int Folder) {
        domain.Folder res = FolderRepository.findOne(Folder);
        Assert.notNull(res);
        return res;
    }

    public Folder save(Folder a) {
        Assert.notNull(a);
        Folder res = FolderRepository.save(a);
        return res;
    }

    public void delete(Folder a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        FolderRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



