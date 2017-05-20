package controllers;


import domain.Category;
import domain.Question;
import domain.Search;
import domain.SearchCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CategoryService;
import services.OtherService;
import services.SearchCacheService;
import services.SearchService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


@Controller
@RequestMapping("/search")
public class SearchController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private SearchService searchService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OtherService otherService;
    @Autowired
    private SearchCacheService searchCacheService;
    //Constructors----------------------------------------------


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView finderList() {

        ModelAndView result;
        Collection<Search> finders = new HashSet<>();


        result = new ModelAndView("search/list");
        result.addObject("searchs", finders);
        result.addObject("requestURI", "search/list.do");


        return result;
    }


    //Create Method -----------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

        Search search = searchService.create();

        Collection<Category> categories = categoryService.findAll();
        result = createEditModelAndView(search);
        result.addObject("categories", categories);

        return result;

    }

    // Edition ---------------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int searchId) {
        ModelAndView result;
        Search search;

        search = searchService.findOne(searchId);
        Assert.notNull(search);
        result = createEditModelAndView(search);

        return result;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView register(@Valid Search search, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            result = createEditModelAndView(search);
        } else {
            try {

                otherService.findByPrincipal().getSearches().add(search);
                search.setOwner(otherService.findByPrincipal());
                Collection<Question> resut;
                if (search.getCategory() == null) {
                    resut = searchService.questionsByKeyword(search.getKeyword());
                } else {
                    resut = searchService.questionsByKeywordAndCategory(search.getKeyword(), search.getCategory());
                }


                result = new ModelAndView("question/list");
                result.addObject("questions", resut);
            } catch (Throwable oops) {
                result = createEditModelAndView(search, "general.commit.error");
            }
        }
        return result;
    }


    @RequestMapping(value = "/edit2", method = RequestMethod.GET)
    public ModelAndView research(@RequestParam int searchId) {
        ModelAndView result;


        Search search = searchService.findOne(searchId);

        Collection<Question> resut = new ArrayList<>();
        if (search.getCategory() == null) {
            resut = searchService.questionsByKeyword(search.getKeyword());
        } else {
            resut = searchService.questionsByKeywordAndCategory(search.getKeyword(), search.getCategory());
        }


        result = new ModelAndView("question/list");
        result.addObject("questions", resut);

        return result;
    }


//    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
//    public ModelAndView delete(Search search){
//        ModelAndView result;
//        try{
//            searchService.delete(search);
//            result=new ModelAndView("redirect:list.do");
//        }catch(Throwable oops){
//            result= createEditModelAndView(search, "general.commit.error");
//        }
//
//        return result;
//    }

    // Ancillary methods ------------------------------------------------


    @RequestMapping(value = "/mySearches", method = RequestMethod.GET)
    public ModelAndView myLastSearches() {


        ModelAndView res;
        Collection<Search> searches = searchService.trunkedSearch();


        res = new ModelAndView("search/list");
        res.addObject("searches", searches);

        return res;


    }


    @RequestMapping(value = "/editCache", method = RequestMethod.GET)
    public ModelAndView editSearchCache() {

        ModelAndView res;
        List<SearchCache> searchCaches = new ArrayList<>(searchCacheService.findAll());
        res = new ModelAndView("search-cache/edit");
        res.addObject("searchcache", searchCaches.get(0));

        return res;

    }

    @RequestMapping(value = "/changeCache", method = RequestMethod.POST, params = "save")
    public ModelAndView changeSearchCache(@Valid SearchCache searchCache) {

        ModelAndView res;
//
//        Authority authority = new Authority();
//        authority.setAuthority("ADMIN");
//
//        if(otherService.findByPrincipal().getUserAccount().getAuthorities().contains(authority)){

        //TODO comprobar identidad de administrador antes de cambiar.
        searchCacheService.save(searchCache);
        res = new ModelAndView("administrator/action-1");

//        }else{
//            res =  new ModelAndView("/welcome/index");
//
//        }

        return res;


    }


    protected ModelAndView createEditModelAndView(Search search) {
        ModelAndView result;

        result = createEditModelAndView(search, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Search search, String message) {
        ModelAndView result;

        result = new ModelAndView("search/edit");
        result.addObject("search", search);
        result.addObject("message", message);


        return result;

    }


}
