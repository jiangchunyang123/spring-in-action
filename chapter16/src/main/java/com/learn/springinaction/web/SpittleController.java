package com.learn.springinaction.web;


import com.learn.springinaction.dao.SpittleRepository;
import com.learn.springinaction.model.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping({"/spittles"})
public class SpittleController {
    private static final String MAX_LONG_AS_STRING = "923372036854775807";
    private SpittleRepository spittleRepository;

    public SpittleController() {
    }

    @Autowired
    public SpittleController(SpittleRepository spitterService) {
        this.spittleRepository = spitterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping("/spittles")
    public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max, int count) {
        return spittleRepository.findSpittles(max, count);

    }
}
