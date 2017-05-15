package services;


import domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CategoryRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class CategoryService {

    // Constructors--------------------------------------------------------------------------------------

    public CategoryService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private CategoryRepository categoryRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Category create() {
        Category res;
        res = new Category();
        return res;
    }

    public Collection<Category> findAll() {
        Collection<Category> res;
        res = categoryRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Category findOne(int Cate) {
        Category res;
        res = categoryRepository.findOne(Cate);
        Assert.notNull(res);
        return res;
    }

    public Category save(Category a) {
        Assert.notNull(a);
        Category res;
        res = categoryRepository.save(a);
        return res;

    }

    public void delete(Category a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        categoryRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



