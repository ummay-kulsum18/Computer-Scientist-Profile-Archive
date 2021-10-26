package com.springapp.mvc.controller;

import com.springapp.mvc.beans.*;
import com.springapp.mvc.dao.scientistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**

 * Created by Jui on 24/4/2017.
 */
@Controller
public class searchController {
    @Autowired
    scientistDao dao;

    //@RequestMapping("/")
    @RequestMapping(value = "/", method = RequestMethod.GET)
   public ModelAndView printWelcome(ModelMap model) {
        model.addAttribute("message", "Computer Scientists Profile Archive");
        //System.out.println("startinggggggggggggggggggggggg");
        List<pictures> pic = dao.getAllPictures();
       // System.out.println(pic.size()+"***************");
        pictures pic1 = pic.get(0);
        model.addAttribute("pic1",pic1);
        pictures pic2 = pic.get(1);
        model.addAttribute("pic2",pic2);
        pictures pic3 = pic.get(2);
        model.addAttribute("pic3",pic3);
        pictures pic4 = pic.get(3);
        model.addAttribute("pic4",pic4);
        return  new ModelAndView("hello","pic",pic);
    }
    //@RequestMapping(method = RequestMethod.GET)
   /* public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Computer Scientists Profile Archive");
        System.out.println("startinggggggggggggggggg");
        return "hello";
    }*/
    @RequestMapping("/nav")
    public ModelAndView shownav(HttpServletRequest request) {
        String str = request.getParameter("button");
        if(str.equals("home")) return new ModelAndView("redirect:/");
        if(str.equals("about")) return new ModelAndView("about");
        if(str.equals("login")) {
            if(awardController.login){
                List<Message> listmes = dao.getMessage();
                return new ModelAndView("homePage","listmes",listmes);
            }
            else {
                return new ModelAndView("login");
            }

        }
        if(str.equals("logout")){
            awardController.login=false;
            return new ModelAndView("login");
        }
        if(str.equals("join")) return new ModelAndView("createAccount","command", new Accountinfo());
        return new ModelAndView("redirect:/searchscientist");
    }
    @RequestMapping("/searchscientist")
    public ModelAndView showform() {
       // System.out.println("hereeeeeeeeeeeeeeeeee");
        return new ModelAndView("searchOption", "command", new scientist());
    }
    @RequestMapping(value = "/backdetail/{name}")
    public ModelAndView bdetail(@PathVariable String name) {
        List<scientist> sn = dao.getScientist(name);
       // System.out.println(awardController.username+"*************************************");
        //List<String> scn = dao.getScientstName(s.getName());
        return new ModelAndView("Scientistdetail","sn",sn) ;
    }
    @RequestMapping(value = "/nameSelected/{name}")
    public ModelAndView showdetail(@PathVariable String name) {
        List<scientist> sn = dao.getScientist(name);
        //List<String> scn = dao.getScientstName(s.getName());
        return new ModelAndView("Scientistdetail","sn",sn) ;
    }
    @RequestMapping(value = "/searchByName", method = RequestMethod.POST)
    public ModelAndView guestSession(scientist s,ModelMap model) {
        //List<scientist> scn = dao.getScientist(s.getName());
        if(s.getName().equals("")) return new ModelAndView("searchOption", "command", new scientist());
        else {
            List<String> scn = dao.getScientstName(s.getName());
            if(scn.isEmpty()) model.addAttribute("no","No such search result found");
            return new ModelAndView("search", "scn", scn);
        }
    }

    @RequestMapping("/scientist")
    public String aboutScientist() {
        return "search";
    }
    @RequestMapping(value = "/searchByAward/{name}/{dob}")
    public ModelAndView award(@PathVariable String name,@PathVariable String dob,ModelMap model) {
        List<awardTable> at = dao.getAward(name,dob);
        model.addAttribute("name",name);
        return new ModelAndView("award", "at", at);
    }
    @RequestMapping(value = "/searchforKnown/{name}/{dob}")
    public ModelAndView knownfor(@PathVariable String name,@PathVariable String dob,ModelMap model) {
        List<knownFor> kf = dao.getKnownFor(name,dob);
        model.addAttribute("name",name);
        return new ModelAndView("knownfor", "kf", kf);
    }
    @RequestMapping(value = "/searchforAlma/{name}/{dob}")
    public ModelAndView alma(@PathVariable String name,@PathVariable String dob,ModelMap model) {
        List<alma_mater> al = dao.getAlmaMater(name, dob);
        model.addAttribute("name",name);
        return new ModelAndView("almaMater", "al", al);
    }



    @RequestMapping(value = "/image/{name}", method = RequestMethod.GET)
    @ResponseBody
    public byte[]  loadImage(@PathVariable String name) {
        List<scientist> sn = dao.getScientist(name);
        return sn.get(0).getPicture();
    }



}
