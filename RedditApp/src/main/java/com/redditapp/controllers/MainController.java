package com.redditapp.controllers;

import com.redditapp.services.RedditPostService;
import com.redditapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    RedditPostService redditPostService;
    UserService userService;

    @Autowired
    public MainController(RedditPostService redditPostService, UserService userService) {
        this.redditPostService = redditPostService;
        this.userService = userService;
    }

    // INDEX PAGE

    @GetMapping({"/", "/home"})
    public String homepage(
            @RequestParam(name = "username", required = false) String currentUser,
            @RequestParam(name = "page", required = false, defaultValue = "0") String pageNum,
            Model model) {
        model.addAttribute("posts", redditPostService.showAll(Integer.parseInt(pageNum)));
        model.addAttribute("currentPage", Integer.parseInt(pageNum));
        model.addAttribute("lastPage", redditPostService.lastPage(Integer.parseInt(pageNum)));
        model.addAttribute("databaseSize", redditPostService.databaseSize());
        model.addAttribute("currentUser", currentUser);
        return "index";
    }

    // UPVOTE AND DOWNVOTE

    @GetMapping("/upvote")
    public String upvote(@RequestParam(name = "username", defaultValue = "anonymous") String currentUser,
                         @RequestParam(name = "id") long id,
                         @RequestParam(name = "page", required = false, defaultValue = "0") String pageNum,
                         Model model) {
        if (!currentUser.equals("anonymous")) {
            redditPostService.vote(id, currentUser, true);
            return "redirect:/?username=" + currentUser + "&page=" + pageNum;
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/downvote")
    public String downvote(@RequestParam(name = "username", defaultValue = "anonymous") String currentUser,
                           @RequestParam(name = "id") long id,
                           @RequestParam(name = "page", required = false, defaultValue = "0") String pageNum,
                           Model model) {
        if (!currentUser.equals("anonymous")) {
            redditPostService.vote(id, currentUser, false);
            return "redirect:/?username=" + currentUser + "&page=" + pageNum;
        } else {
            return "redirect:/login";
        }
    }

    // SUBMIT NEW POST

    @GetMapping("/submit")               // submit form, before submitting
    public String submitPost(@RequestParam(name = "username") String currentUser, Model model) {
        model.addAttribute("currentUser", currentUser);
        return "submit";
    }

    @PostMapping("/submit")             // after clicking submit post
    public String submitPost(
            @RequestParam(name = "username") String currentUser,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "url") String url,
            Model model) {
        redditPostService.submitNew(title, url, currentUser);
        return "redirect:/?username=" + currentUser;
    }

// REGISTRATION

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model) {
        if (userService.createUser(username, password)) {
            return "redirect:/?username=" + username;
        } else {
            return "redirect:/invalidRegistration";
        }
    }

    @GetMapping("/invalidRegistration")
    public String invalidRegistration() {
        return "invalidRegistration";
    }

    // LOGIN
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model) {
        // if jméno i heslo sedí, redirect na home+param je username
        if (userService.loginSuccessful(username, password)) {
            return "redirect:/?username=" + username;
        } else {
            return "redirect:/invalidLogin";
        }
    }

    @GetMapping("/invalidLogin")
    public String invalidLogin() {
        return "invalidLogin";
    }
}







 /*   @GetMapping({"/", "/home"})                    // homepage - list all posts
    public String homepage(Model model) {
        model.addAttribute("posts", redditPostService.showAll());
        return "index";
    }*/

/*
    @PostMapping("/register")
    public String registerNewUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model) {
        if (userService.createUser(username, password)) {
            return "redirect:/?username=" + username;
        } else {
            model.addAtribute("invalidUsername", true)
            return "redirect:/register";
        }
    }
 */