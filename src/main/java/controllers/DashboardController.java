/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package controllers;


import domain.Actor;
import domain.Teacher;
import domain.User;
import domain.Webinar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.AdministratorService;
import services.TeacherService;

import java.util.Collection;


@Controller
@RequestMapping("/admin")
public class DashboardController extends AbstractController {

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private TeacherService teacherService;


    public DashboardController() {
        super();
    }

    @RequestMapping(value = "/dashboard")
    public ModelAndView dashboard() {
        ModelAndView res;


        Double q1 = administratorService.averageNumberOfQuestionPerUser();
        Double q2 = administratorService.averageNumberOfAnswerPerUser();
        Double q3 = administratorService.averageNumberOfWebinarsPerTeacher();
        Double q4 = administratorService.averageNumberOfCommentsPerWebinar();
        Double q5 = administratorService.averageNumberOfBannedQuestionPerUser();
        Double q6 = administratorService.averageNumberOfBannedAnswerPerUser();


        int q7 = administratorService.maxNumberOfQuestionPerUser();
        int q8 = administratorService.minNumberOfQuestionPerUser();
        int q9 = administratorService.maxNumberOfAnswerPerUser();
        int q10 = administratorService.minNumberOfAnswerPerUser();
        int q11 = administratorService.maxNumberOfCommentPerWebinar();
        int q12 = administratorService.minNumberOfCommentPerWebinar();

        Collection<User> q15 = administratorService.userSortedByQuestionNumber();
        Collection<User> q16 = administratorService.userSortedByNumberOfLikes();
        Collection<User> q17 = administratorService.usersWithCreditCards();
        Collection<User> q18 = administratorService.userSortedByNumberOfQuestionBanned();
        Collection<User> q19 = administratorService.userSortedByNumberOfAnswerBanned();

        Collection<Actor> q20 = administratorService.actorSortedByNumberOfSendMessage();
        Collection<Actor> q21 = administratorService.actorSortedByNumberOfReceivedMessages();


        Collection<Teacher> q22 = administratorService.teachersSortedByNumberOfWebinars();
        Collection<Webinar> q23 = administratorService.webinarSortedByNumberOfAssistance();

        String q24 = administratorService.percentageofBannedUser();
        String q25 = administratorService.percentageofBannedAnswer();
        String q26 = administratorService.percentageOfBannedQuestion();


        res = new ModelAndView("administrator/dashboard");
        res.addObject("q1", q1);
        res.addObject("q2", q2);
        res.addObject("q3", q3);
        res.addObject("q4", q4);
        res.addObject("q5", q5);
        res.addObject("q6", q6);
        res.addObject("q7", q7);
        res.addObject("q8", q8);
        res.addObject("q9", q9);
        res.addObject("q10", q10);
        res.addObject("q11", q11);
        res.addObject("q12", q12);
        res.addObject("q15", q15);
        res.addObject("q16", q16);
        res.addObject("q17", q17);
        res.addObject("q18", q18);
        res.addObject("q19", q19);
        res.addObject("q20", q20);
        res.addObject("q21", q21);
        res.addObject("q22", q22);
        res.addObject("q23", q23);
        res.addObject("q24", q24);
        res.addObject("q25", q25);
        res.addObject("q26", q26);


        return res;
    }


    @RequestMapping(value = "/dashboardT")
    public ModelAndView dashboardT() {
        ModelAndView res;


        Double qq1 = teacherService.averageNumberOfUserInMyWebinar(teacherService.findByPrincipal());
        int qq2 = teacherService.maxNumberOfUserInMyWebinar(teacherService.findByPrincipal());
        int qq3 = teacherService.minNumberOfUserInMyWebinar(teacherService.findByPrincipal());
        Collection<Webinar> qq4 = teacherService.webinarSortedByNumberOfUsers(teacherService.findByPrincipal());
        Collection<User> qq5 = teacherService.userRegisteredInMyWebinars(teacherService.findByPrincipal());


        res = new ModelAndView("teacher/dashboard");

        res.addObject("qq1", qq1);
        res.addObject("qq2", qq2);
        res.addObject("qq3", qq3);
        res.addObject("qq4", qq4);
        res.addObject("qq5", qq5);


        return res;
    }
}
