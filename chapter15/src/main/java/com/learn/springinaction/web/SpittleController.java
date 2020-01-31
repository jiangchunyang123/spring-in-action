package com.learn.springinaction.web;


import com.learn.springinaction.model.Spitter;
import com.learn.springinaction.service.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
@RequestMapping({"/spitter", "/homepage"})
public class SpittleController {

    private SpitterService spitterService;

    public SpittleController() {
    }

    @Autowired
    public SpittleController(SpitterService spitterService) {
        this.spitterService = spitterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping("/spittles")
    public String spittles(Model model) {
        model.addAttribute(spitterService.getRecentSpittles(20));
        return "spittles";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new Spitter());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistion(Spitter spitter, @RequestPart() byte[] file) {
        spitterService.saveSpitter(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }

}
