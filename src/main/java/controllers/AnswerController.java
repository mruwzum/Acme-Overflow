/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package controllers;


import domain.Actor;
import domain.Answer;
import domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.Authority;
import services.ActorService;
import services.AnswerService;
import services.QuestionService;
import services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/answer")
public class AnswerController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActorService actorService;

    //Constructors----------------------------------------------

    public AnswerController() {
        super();
    }

    protected static ModelAndView createEditModelAndView(Answer answer) {
        ModelAndView result;

        result = createEditModelAndView(answer, null);

        return result;
    }


    //Create Method -----------------------------------------------------------

    protected static ModelAndView createEditModelAndView(Answer answer, String message) {
        ModelAndView result;

        result = new ModelAndView("answer/edit");
        result.addObject("answer", answer);
        result.addObject("message", message);

        return result;

    }

    protected static ModelAndView createEditModelAndView2(Answer answer) {
        ModelAndView result;

        result = createEditModelAndView2(answer, null);

        return result;
    }
    // Edition ---------------------------------------------------------

    protected static ModelAndView createEditModelAndView2(Answer answer, String message) {
        ModelAndView result;

        result = new ModelAndView("answer/editLess");
        result.addObject("answer", answer);
        result.addObject("message", message);

        return result;

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView answerList() {

        ModelAndView result;
        Collection<Answer> answers;


        Collection<Answer> res = answerService.all();
        result = new ModelAndView("answer/list");
        result.addObject("answers", res);
        result.addObject("requestURI", "answer/list.do");

        return result;
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public ModelAndView answerListAll() {

        ModelAndView result;
        Collection<Answer> answers;
        answers = answerService.all();

        result = new ModelAndView("answers/list");
        result.addObject("answers", answers);
        result.addObject("requestURI", "answers/list.do");

        return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam int questionId) {

        ModelAndView result;

        Question question = questionService.findOne(questionId);
        Answer answer = answerService.create();
        answer.setQuestion(question);
        answer.setBanned(false);
        answer.setTeacher(false);
        answer.setLikedActors(new ArrayList<Actor>());
        result = createEditModelAndView(answer);

        return result;

    }

    // Ancillary methods ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int answerId) {
        ModelAndView result;
        Answer answer;

        answer = answerService.findOne(answerId);
        Assert.notNull(answer);
        result = createEditModelAndView(answer);

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Answer answer, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            result = createEditModelAndView(answer);
        } else {
            try {

                Authority authority = new Authority();
                authority.setAuthority("TEACHER");
                if (actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority)) {
                    answer.setOwner(actorService.findByPrincipal());
                    answer.setTeacher(true);
                    answer.setQuestion(answer.getQuestion());
                    answerService.save(answer);

                } else {
                    answer.setOwner(actorService.findByPrincipal());
                    answer.setQuestion(answer.getQuestion());
                    answerService.save(answer);

                }


                result = new ModelAndView("user/success");
            } catch (Throwable oops) {
                result = createEditModelAndView(answer, "general.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Answer answer) {
        ModelAndView result;
        try {
            answerService.delete(answer);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(answer, "general.commit.error");
        }

        return result;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam int answerId) {
        ModelAndView result;


        Answer answer = answerService.findOne(answerId);
        answer.setOwner(null);
        answer.setQuestion(null);
        answerService.delete(answer);
        result = new ModelAndView("user/success");
        return result;
    }

    @RequestMapping(value = "ban", method = RequestMethod.GET)
    public ModelAndView ban(@RequestParam int answerId) {
        ModelAndView result;
        Boolean opq;
        Answer answer = answerService.findOne(answerId);
        opq = answerService.banAnswer(answer);

        if (opq.equals(false)) {
            result = new ModelAndView("user/error");
        } else {
            result = new ModelAndView("user/success");
        }


        return result;
    }

    @RequestMapping(value = "unban", method = RequestMethod.GET)
    public ModelAndView unban(@RequestParam int answerId) {
        ModelAndView result;
        Boolean op;
        Answer answer = answerService.findOne(answerId);
        op = answerService.unbanAnswer(answer);

        if (op.equals(false)) {
            result = new ModelAndView("user/error");
        } else {
            result = new ModelAndView("user/success");
        }


        return result;
    }

    @RequestMapping(value = "ratepositive", method = RequestMethod.GET)
    public ModelAndView ratepositive(@RequestParam int answerId) {
        ModelAndView result;
        Answer answer = answerService.findOne(answerId);
        int likes = answer.getLikes();
        answer.setLikes(likes + 1);
        answer.getLikedActors().add(actorService.findByPrincipal());
        actorService.findByPrincipal().getLikedAnswers().add(answer);
        answerService.save(answer);


        result = new ModelAndView("user/success");
        return result;
    }

    @RequestMapping(value = "ratenegative", method = RequestMethod.GET)
    public ModelAndView ratenegative(@RequestParam int answerId) {
        ModelAndView result;
        Answer answer = answerService.findOne(answerId);
        Actor actor = actorService.findByPrincipal();
        answer.getLikedActors().remove(actor);
        actor.getLikedAnswers().remove(answer);

        int likes = answer.getLikes();
        answer.setDislikes(likes - 1);
        answerService.save(answer);
        result = new ModelAndView("user/success");
        return result;
    }

}
