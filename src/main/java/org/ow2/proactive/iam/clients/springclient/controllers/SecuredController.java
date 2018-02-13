package org.ow2.proactive.iam.clients.springclient.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/secured")
public class SecuredController {

    /*@GetMapping
    public String index (HttpServletRequest request)
    {
        //modelMap.put("userName", request.getRemoteUser());
        //modelMap.put("serviceTicket", "");

        //System.out.println("2"+auth.getName());
        //System.out.println("2"+auth.getCredentials().toString());

        System.out.println("1"+request.getRemoteUser());
        //return request.getRemoteUser();
        return "secure/secure";
    }*/

    @GetMapping//(value = "/secured")
    //,@ModelAttribute("user") String user
    public String go(ModelMap modelMap) {
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if( auth != null && auth.getPrincipal() != null
                && auth.getPrincipal() instanceof UserDetails) {
            modelMap.put("userName", ((UserDetails) auth.getPrincipal()).getUsername());
            modelMap.put("serviceTicket", auth.getCredentials().toString());

            System.out.println("2"+auth.getName());
            System.out.println("2"+auth.getCredentials().toString());

        }*/
        return "secure";
    }
}