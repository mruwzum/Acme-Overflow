package controllers;


import domain.Category;
import domain.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.QuestionService;
import services.UserService;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/question")
public class QuestionController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;


    //Constructors----------------------------------------------

    public QuestionController() {
        super();
    }

    protected static ModelAndView createEditModelAndView(Question question) {
        ModelAndView result;

        result = createEditModelAndView(question, null);

        return result;
    }


    //Create Method -----------------------------------------------------------

    protected static ModelAndView createEditModelAndView(Question question, String message) {
        ModelAndView result;

        result = new ModelAndView("question/edit");
        result.addObject("question", question);
        result.addObject("message", message);

        return result;

    }

    protected static ModelAndView createEditModelAndView2(Question question) {
        ModelAndView result;

        result = createEditModelAndView2(question, null);

        return result;
    }
    // Edition ---------------------------------------------------------

    protected static ModelAndView createEditModelAndView2(Question question, String message) {
        ModelAndView result;

        result = new ModelAndView("question/editLess");
        result.addObject("question", question);
        result.addObject("message", message);

        return result;

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView questionList() {

        ModelAndView result;
        Collection<Question> questions;

       Collection<Question> res = new HashSet<>();
        questions = questionService.findAll();
       for (Question question : questions) {
          if (!question.isBanned()) {
             res.add(question);
          }
       }
        result = new ModelAndView("question/list");
       result.addObject("questions", res);
       result.addObject("requestURI", "question/list.do");

       return result;
    }

   @RequestMapping(value = "/listAll", method = RequestMethod.GET)
   public ModelAndView questionListAll() {

      ModelAndView result;
      Collection<Question> questions;
      questions = questionService.findAll();

      result = new ModelAndView("question/list");
      result.addObject("questions", questions);
      result.addObject("requestURI", "question/list.do");

      return result;
   }
   @RequestMapping(value = "/listPopular", method = RequestMethod.GET)
   public ModelAndView questionListPopular() {

      ModelAndView result;
      List<Question> questions = new ArrayList<>(questionService.findAll());

      Collections.sort(questions, new Comparator<Question>() {
         public int compare(Question m1, Question m2) {
            return m2.getCreatedDate().toString().compareTo(m1.getCreatedDate().toString());
         }
      });

      result = new ModelAndView("question/list");
      result.addObject("questions", questions);
      result.addObject("requestURI", "question/list.do");

      return result;
   }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

        Question question = questionService.create();
        Collection<Category> categories = categoryService.findAll();

        result = createEditModelAndView(question);
        result.addObject("categories", categories);

        return result;

    }

    // Ancillary methods ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int questionId) {
        ModelAndView result;
        Question question;

        question = questionService.findOne(questionId);
        Assert.notNull(question);
        result = createEditModelAndView(question);

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Question question, BindingResult binding) {
        ModelAndView result;
        if (!binding.hasErrors()) {
            result = createEditModelAndView(question);
        } else {
            try {
                question.setOwner(userService.findByPrincipal());
                question.setCreatedDate(new Date(System.currentTimeMillis()-1000));
                questionService.save(question);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(question, "question.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Question question) {
        ModelAndView result;
        try {
            questionService.delete(question);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(question, "question.commit.error");
        }

        return result;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView lessorView(@RequestParam int questionId) {

        ModelAndView result;
        Question question = questionService.findOne(questionId);


        result = new ModelAndView("question/view");
        result.addObject("title", question.getTitle());
        result.addObject("summary", question.getSummary());
        result.addObject("owner", question.getOwner().getName());
        result.addObject("categorie", question.getCategories().toString());
        result.addObject("answers", question.getAnswers());
        result.addObject("questionId", question.getId());
        result.addObject("requestURI", "question/view.do");

        return result;
    }

   @RequestMapping(value = "ban", method = RequestMethod.GET)
   public ModelAndView ban(@RequestParam int questionId) {
      ModelAndView result;
      Boolean opq;
      Question answer = questionService.findOne(questionId);
      opq = questionService.banQuestion(answer);

      if (opq.equals(false)) {
         result = new ModelAndView("user/error");
      } else {
         result = new ModelAndView("redirect:listAll.do");
      }


      return result;
   }

   @RequestMapping(value = "unban", method = RequestMethod.GET)
   public ModelAndView unban(@RequestParam int questionId) {
      ModelAndView result;
      Boolean op;
      Question answer = questionService.findOne(questionId);
      op = questionService.unbanQuestion(answer);

      if (op.equals(false)) {
         result = new ModelAndView("user/error");
      } else {
         result = new ModelAndView("redirect:listAll.do");
      }


      return result;
   }
}
