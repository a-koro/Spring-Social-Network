/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connector.beta.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author korov
 */

@Controller
public class AuthenticationController {
    
    
    // The next contoller doesnt do anything it just prints the return statement
    // in the browser
    @GetMapping("/welcome")
    //@ResponseBody
    public String welcomePage() {
        //return "/WEB-INF/jsp/build/index.html";
        return "welcome";
    }

//    
//    @CrossOrigin(origins = "http://localhost:3000")
//    @GetMapping("/koro")
//    @ResponseBody
//    public String koro() {
//        System.out.println("KOROOOOOOOOOOOOOOOOOOOOO");
//        return "Kala ta kataferes";
//    }
}
