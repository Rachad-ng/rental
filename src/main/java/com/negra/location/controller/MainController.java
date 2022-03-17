package com.negra.location.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private static final String ABOUT = "about";
    private static final String LISTINGS = "listings";
    private static final String LISTINGS_DETAILS = "listings-details";
    private static final String HOW_IT_WORKS = "how-it-works";
    private static final String NEWS = "news";
    private static final String NEWS_DETAILS = "news-details";
    private static final String CONTACT = "contact";

    @GetMapping ("/about")
    public String about(){
        return ABOUT;
    }

    @GetMapping("/listings")
    public String listings(){
        return LISTINGS;
    }

    @GetMapping("/listingsDetails")
    public String listingsDetails(){
        return LISTINGS_DETAILS;
    }

    @GetMapping("/news")
    public String news(){
        return NEWS;
    }

    @GetMapping("/newsDetails")
    public String newsDetails(){
        return NEWS_DETAILS;
    }

    @GetMapping("/contact")
    public String contact(){
        return CONTACT;
    }

    @GetMapping("/howItWorks")
    public String howItWorks(){
        return HOW_IT_WORKS;
    }

}
