package com.springapp.mvc.controller;

import com.springapp.mvc.beans.*;
import com.springapp.mvc.dao.awardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jui on 5/5/2017.
 */
@Controller
public class awardController {
    public static String username;
    public static boolean login=false;
    @Autowired
    awardDao adao;
    @RequestMapping("/searchAward")
    public ModelAndView showOption() {
        return new ModelAndView("awardOption", "command", new awardTable());
    }

    @RequestMapping(value = "/findByAward", method = RequestMethod.POST)
    public ModelAndView showName(@RequestParam("a_name") String a_name,@RequestParam("year")String year ){
        if(!(a_name.equals("")) && !(year.equals("")) ){
            List<awardTable> aw = adao.getNames(a_name,year);
            System.out.println(a_name+"*****************************************");
            System.out.println(year);
            return new ModelAndView("awardyear","aw",aw) ;
        }
        if(!(a_name.equals(""))){
            System.out.println(a_name+"*****************************************");
            List<awardTable> aw = adao.getNamesbyaward(a_name);

            return new ModelAndView("awardyear","aw",aw) ;
        }
        else if(!(year.equals(""))){
            return new ModelAndView("awardOption", "command", new awardTable());
        }

        return new ModelAndView("awardOption", "command", new awardTable());
    }
//********************************************************************************************
    @RequestMapping("/searchByIns")
    public ModelAndView shownamebtIns(HttpServletRequest request,checkOption co,ModelMap model) {
        String ins=request.getParameter("institution");
        if(co.getChoice() == null) return new ModelAndView("redirect:/searchInstitute");
        List<String> s= co.getChoice();

        System.out.println(s.size()+"*********"+s.get(0));
        if(ins.equals(""))  return new ModelAndView("redirect:/searchInstitute");
       // if(s == null) return new ModelAndView("redirect:/searchInstitute");
        if(s.size()==1 && s.get(0).equals("Now working")){
            List<String> ns = adao.getnamebyInsW(ins);
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==1 && s.get(0).equals("PhD")){
            List<String> ns = adao.getnamebyInsP(ins,s.get(0));
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==1 && s.contains("B.S.")){
            List<String> ns = adao.getnamebyInsP(ins,"B.S.");
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==1 && s.contains("M.S.")){
            List<String> ns = adao.getnamebyInsP(ins,"M.S.");
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==2 && (s.contains("M.S.") && s.contains("B.S."))){
            List<String> ns = adao.getnamebyInsbm(ins,"M.S.","B.S.");
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==2 && (s.contains("M.S.") && s.contains("PhD"))){
            List<String> ns = adao.getnamebyInsbm(ins,"M.S.","PhD");
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==2 && (s.contains("B.S.") && s.contains("PhD"))){
            List<String> ns = adao.getnamebyInsbm(ins,"B.S.","PhD");
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==2 && (s.contains("Now working") && s.contains("PhD"))){
            List<String> ns = adao.getnamebyInsWP(ins,"PhD");
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==2 && (s.contains("Now working") && s.contains("M.S."))){
            List<String> ns = adao.getnamebyInsWP(ins,"M.S.");
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==2 && (s.contains("Now working") && s.contains("B.S."))){
            List<String> ns = adao.getnamebyInsWP(ins,"B.S.");
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==3 && (s.contains("M.S.") && s.contains("B.S.") && s.contains("PhD"))){
            List<String> ns = adao.getnamebyInsbPm(ins,"M.S.","B.S.","PhD");
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==3 && (s.contains("M.S.") && s.contains("B.S.") && s.contains("Now working"))){
            List<String> ns = adao.getnamebyInsWPm(ins,"M.S.","B.S.");
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==3 && (s.contains("M.S.") && s.contains("PhD") && s.contains("Now working"))){
            List<String> ns = adao.getnamebyInsWPm(ins,"M.S.","PhD");
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==3 && (s.contains("PhD") && s.contains("B.S.") && s.contains("Now working"))){
            List<String> ns = adao.getnamebyInsWPm(ins,"PhD","B.S.");
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }
        else if(s.size()==4 ){
            List<String> ns = adao.getnamebyInsAll(ins,"PhD","B.S.","M.S.");
            if(ns.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("/search", "scn", ns);
        }


        return new ModelAndView("redirect:/searchInstitute");
        //return new ModelAndView("awardOption", "command", new awardTable());
    }

    //********************************************************************************************
    @RequestMapping("/searchByNation")
    public ModelAndView shownamebyNation(HttpServletRequest request,ModelMap model) {
        String n=request.getParameter("nationality");
        if(n.equals(""))  return new ModelAndView("redirect:/searchNationality");
        List<String> ns = adao.getnamebyNation(n);
        if(ns.isEmpty()) model.addAttribute("no","No such search result found");
        return new ModelAndView("/search", "scn", ns);
    }
    @RequestMapping("/searchInstitute")
    public ModelAndView insOption(ModelMap model) {
        checkOption co = new checkOption();
        List<String> choice = new ArrayList<String>();
        choice.add("Now working");
        choice.add("PhD");
        choice.add("B.S.");
        choice.add("M.S.");
        model.addAttribute("choice", choice);
        return new ModelAndView("InsOption", "command",co);
    }

    @RequestMapping("/searchNationality")
    public String nationOption() {
        return "nationalityOptn";
    }

    @RequestMapping("/searchThesis")
    public String thesisOption() {
        return "thesisOptn";
    }
    @RequestMapping("/searchByThesis")
    public ModelAndView shownamebyThesis(HttpServletRequest request,ModelMap model) {
        String n=request.getParameter("thesis");
        if(n.equals(""))  return new ModelAndView("redirect:/searchNationality");
        List<String> ns = adao.getnamebyThesis(n);
        if(ns.isEmpty()) model.addAttribute("no","No such search result found");
        return new ModelAndView("/search", "scn", ns);
    }










    //************************************Account Controller**************************************************//

    @RequestMapping(value = "/accountCreate", method = RequestMethod.POST)
    public ModelAndView account(Accountinfo a,ModelMap model) {
        if(a.getFname().equals("")){
            model.addAttribute("errormes","**Please provide Frist name");
            return new ModelAndView("createAccount","command", new Accountinfo());
        }
        if(a.getLname().equals("")){
            model.addAttribute("errormes","**Please provide Last name");
            return new ModelAndView("createAccount","command", new Accountinfo());
        }
        if(a.getEmail().equals("")){
            model.addAttribute("errormes","**Please provide Email Address");
            return new ModelAndView("createAccount","command", new Accountinfo());
        }
        if(a.getUname().equals("")){
            model.addAttribute("errormes","**Please provide  Username");
            return new ModelAndView("createAccount","command", new Accountinfo());
        }
        if(a.getPassword().length()<5 || a.getPassword().length()>20){
            model.addAttribute("errormes","**Password must be of length at least 5 and atmost 20");
            return new ModelAndView("createAccount","command", new Accountinfo());
        }
        int success = adao.createAccount(a);

        if(success<=0){
            model.addAttribute("mes","Please Try again");

        }
        else{
            model.addAttribute("mes","You have successfully joined the group");
        }

        return new ModelAndView ("accountSuccess");


    }


    @RequestMapping(value = "/varifyLogin", method = RequestMethod.POST)
    public ModelAndView verifyaccount(HttpServletRequest request,ModelMap model) {

        String uname = request.getParameter("username");
        String password = request.getParameter("password");
        boolean exist = adao.existUser(uname,password);

        if(exist) {
           // model.addAttribute("Username",uname);
            username = uname;
            login=true;
            List<Message> listmes = adao.getMessage();
            return new ModelAndView("homePage","listmes",listmes);
        }

        else
            model.addAttribute("error","Please try with correct username and password");
            return new ModelAndView("login");


    }

    @RequestMapping(value = "/postMessage", method = RequestMethod.POST)
    public ModelAndView postMessage(HttpServletRequest request,ModelMap model) {

        String question = request.getParameter("question");
        if(question.equals("")){
            model.addAttribute("errorempty","Please write your question first");
            List<Message> listmes = adao.getMessage();
            return new ModelAndView("homePage","listmes",listmes);
        }
        System.out.println(question);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        //DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        //Date time = new Date();
        String d= dateFormat.format(date);
        //String t = dateFormat.format(time);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String t= sdf.format(cal.getTime());
       System.out.println( sdf.format(cal.getTime()) );
        int success = adao.postMessage(username,question,d,t);
        if(success<=0){
           // model.addAttribute("mes","Please Try again");
            System.out.println("error");

        }
        else{
           // model.addAttribute("mes","You have successfully joined the group");
            System.out.println("success");
        }
        System.out.println(t+"     "+d + username);
        List<Message> listmes = adao.getMessage();
        return new ModelAndView("homePage","listmes",listmes);


    }

    @RequestMapping(value = "/postAnswer/{mes_id}", method = RequestMethod.POST)
    public ModelAndView postAnswer(HttpServletRequest request,ModelMap model,@PathVariable String mes_id) {

        String answer = request.getParameter("answer");
        if(answer.equals("")){
            model.addAttribute("errorempty","Please write your answer first");
            List<Message> listmes = adao.getMessage();
            return new ModelAndView("homePage","listmes",listmes);
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String d= dateFormat.format(date);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String t= sdf.format(cal.getTime());
        System.out.println( sdf.format(cal.getTime()) );
       int success = adao.postAnswer(Integer.parseInt(mes_id), username, answer, d, t);
        if(success<=0){
            // model.addAttribute("mes","Please Try again");
            System.out.println("error");

        }
        else{
            // model.addAttribute("mes","You have successfully joined the group");
            System.out.println("success");
        }
        System.out.println(t+"     "+d + username);
        List<Message> listmes = adao.getMessage();
        return new ModelAndView("homePage","listmes",listmes);


    }


    @RequestMapping(value = "/searchAnswers/{mes_id}")
    public ModelAndView searchAnswer(HttpServletRequest request,ModelMap model,@PathVariable String mes_id) {
        List<Answer> listans = adao.getAnswer(Integer.parseInt(mes_id));
        /*for(Answer a: listans){
            System.out.println(a.getAnswerer());
        }*/
        if(listans.isEmpty()){
            model.addAttribute("error1","No answer available");
        }
        return new ModelAndView("answer","listans",listans);
        //List<Message> listmes = adao.getMessage();
       // return new ModelAndView("homePage","listmes",listmes);


    }


    @RequestMapping(value = "/back", method = RequestMethod.POST)
    public ModelAndView postAnswer(HttpServletRequest request,ModelMap model) {
        List<Message> listmes = adao.getMessage();
        return new ModelAndView("homePage","listmes",listmes);

    }


}
