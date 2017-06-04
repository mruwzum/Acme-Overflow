/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package services;

import domain.Category;
import domain.Question;
import domain.Search;
import domain.SearchCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.SearchRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class SearchService {

    // Managed Repository ------------------------
    @Autowired
    private SearchRepository searchRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SearchCacheService searchCacheService;
    @Autowired
    private ActorService actorService;

    // Supporting services -----------------------

    // Constructor -------------------------------
    public SearchService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public Search create() {
        Search res;
        res = new Search();
        return res;
    }

    public Search findOne(int actorId) {
        Search result;

        result = searchRepository.findOne(actorId);

        return result;
    }

    public Collection<Search> findAll() {
        Collection<Search> result;

        result = searchRepository.findAll();

        return result;
    }

    public Search save(Search actor) {
        Assert.notNull(actor);
        return searchRepository.save(actor);
    }

    public void delete(Search actor) {
        Assert.notNull(actor);
        Assert.isTrue(searchRepository.exists(actor.getId()));
        searchRepository.delete(actor);
    }

    // Other business methods -----------------------

    public void flush() {
        searchRepository.flush();
    }


    public Collection<Question> questionsByKeyword(String keyword) {


        Assert.notNull(actorService.findByPrincipal());

        Assert.notNull(keyword, "keyword vacía");
        return searchRepository.questionsByKeyword(keyword);
    }


    public Collection<Question> questionsByKeywordAndCategory(String keyword, Category category) {

        Assert.notNull(actorService.findByPrincipal());

        Assert.notNull(keyword, "keyword vacía");
        Assert.notNull(category, "category vacía");
        return searchRepository.questionsByKeywordAndCategory(keyword, category);
    }


    public Collection<Search> trunkedSearch() {

        List<Search> searches = new ArrayList<>(actorService.findByPrincipal().getSearches());
        List<SearchCache> searchCaches = new ArrayList<>(searchCacheService.findAll());
        int searchcac = searchCaches.get(0).getCacheValue();

        if (searches.size() > searchcac) {

            Collection<Search> searches1 = searches.subList(searches.size() - searchcac, searches.size());
            return searches1;

        } else {
            return searches;
        }

    }

}
