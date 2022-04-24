package com.negra.location.controller;

import com.negra.location.dto.HomeSearchCarDto;
import com.negra.location.service.interfaces.ICarService;
import com.negra.location.service.interfaces.ISearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@AllArgsConstructor
@Controller
public class MainController {

    private static final String HOME = "index";
    private static final String ABOUT = "about";
    private static final String HOW_IT_WORKS = "how-it-works";
    private static final String NEWS = "news";
    private static final String NEWS_DETAILS = "news-details";
    private static final String CONTACT = "contact";

    private ICarService carService;
    private ISearchService searchService;

    @GetMapping("/")
    public String home(Model model){

        HomeSearchCarDto homeSearchCarDto = searchService.initializeSearchCarForm();
        model.addAttribute("homeSearchCarDto", homeSearchCarDto);

        Map<String, Object> data = carService.getHomePageListings();
        model.addAttribute("mostPopularCarAndModelWithImageDtos", data.get("mostPopularCarAndModelWithImageDtos"));
        model.addAttribute("hotListingDtos", data.get("hotListingDtos"));

        return HOME;

    }

    @GetMapping ("/about")
    public String about(){
        return ABOUT;
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

/*    Creation d'admin

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AdministratorService administratorService;

    @RequestMapping("/test")
    public void addAdmin(){
        Administrator administrator = new Administrator();
        ObjectInitialisation.adminInitialisation(administrator);
        administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
        administratorService.createAdministrateur(administrator);
    }

 */

}
