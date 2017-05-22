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


//
//    public List<User> davidFinder(Search search){
//
//        List<User> res;
//
//       List<User> todos = new ArrayList<>(userService.findAll());
//
//       if (!search.getCoordinate().getCity().equals("void")){
//           todos.retainAll(searchRepository.findByCity(search.getCoordinate().getCity()));
//        }
//
//       if(!(search.getAge()==null)){
//           int overAge =  search.getAge()+5;
//           int underAge = search.getAge()-5;
//           todos.retainAll(searchRepository.findByAge(underAge,overAge));
//       }
//       if (!(search.getGenre()==null)){
//           todos.retainAll(searchRepository.findByGenre(search.getGenre()));
//       }
//       if (!(search.getRelationship()==null)){
//           todos.retainAll(searchRepository.findByRelationship(search.getRelationship()));
//       }
//       if (!search.getCoordinate().getCountry().equals("")){
//           todos.retainAll(searchRepository.findByCountry(search.getCoordinate().getCountry()));
//       }
//       if (!search.getCoordinate().getProvince().equals("")){
//          todos.retainAll(searchRepository.findByProvince(search.getCoordinate().getProvince()));
//       }
//       if (!search.getCoordinate().getState().equals("")){
//           todos.retainAll(searchRepository.findByState(search.getCoordinate().getState()));
//       }
//       if (!search.getKeyword().equals("")){
//           todos.retainAll(searchRepository.findByKeyword(search.getKeyword()));
//       }
//
//        return todos;
//
//    }
//
//    public Boolean checkCreditCard(CreditCard creditCard){
//        Boolean res = false;
//        Integer yearAct0 = (((new Date(System.currentTimeMillis())).getYear()));
//        String year ="20"+ yearAct0.toString().substring(1);
//        Integer yearAct = new Integer(year);
//        int monthAct = new Date(System.currentTimeMillis()).getMonth();
//        if(creditCard==null){
//            res = false;
//        }else if (creditCard.getExpirationYear()==yearAct && creditCard.getExpirationMonth()<monthAct){
//            res = false;
//            creditCard.setValid(false);
//        }else if (creditCard.getExpirationYear()==yearAct && creditCard.getExpirationMonth()>=monthAct){
//            res =  true;
//            creditCard.setValid(true);
//        }else if (creditCard.getExpirationYear()>=yearAct){
//            res=   true;
//            creditCard.setValid(true);
//        }else if (creditCard.getExpirationYear()<yearAct){
//            res= false;
//            creditCard.setValid(false);
//        }
//        return res;
//    }
//    public void checkTime(Collection<Search> searches){
//        Date actual = new Date(System.currentTimeMillis());
//        List<SearchCache> caches = new ArrayList<>(searchCacheService.findAll());
//        Integer hour = caches.get(0).getCacheValue();
//        Long VALUEZ = hour * 60 * 60 * 1000L;
//        if (caches.isEmpty()){
//
//        }else {
//            for (Search s : searches) {
//                if (Math.abs(s.getCreationDate().getTime() - actual.getTime()) > VALUEZ) {
//                    searches.remove(s);
//                }
//            }
//
//        }
//        }


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
