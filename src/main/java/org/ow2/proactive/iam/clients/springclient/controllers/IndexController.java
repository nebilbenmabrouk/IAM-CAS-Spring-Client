package org.ow2.proactive.iam.clients.springclient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    /*@RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "index";
    }*/
}