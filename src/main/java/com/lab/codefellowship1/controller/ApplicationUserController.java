package com.lab.codefellowship1.controller;

import com.lab.codefellowship1.models.ApplicationUser;
import com.lab.codefellowship1.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationUserController {
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/users")
    public RedirectView createUser(String username, String password, String firstName, String lastName, Date dateOfBirth, String bio){
        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName,dateOfBirth, bio);
        applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/myprofile");
    }

    @GetMapping("/myprofile")
    public String getProfile(Principal principal, Model model){
        ApplicationUser applicationUser = null;
        if(principal != null){
            applicationUser = applicationUserRepository.findByUsername(principal.getName());
        }
        model.addAttribute("viewedUser", applicationUser);
        model.addAttribute("user", principal);
        return "myprofile";
    }

    @GetMapping("/users/{id}")
    public String getOneUser(@PathVariable long id, Principal principal, Model model){
        ApplicationUser userss = applicationUserRepository.findById(id).get();
        model.addAttribute("viewedUser", userss);
        model.addAttribute("user", principal);
        return "myprofile";
    }

    @GetMapping("/users")
    public String getAllUsers(Principal principal, Model model){
        if(principal != null){
            model.addAttribute("user", principal);
        }
        model.addAttribute("allUsers", applicationUserRepository.findAll());
        return "users";
    }


    @GetMapping("/feed")
    public String getUsersInfo(@AuthenticationPrincipal ApplicationUser user, Model model) {
        model.addAttribute("username", user.getUsername());
        ApplicationUser feed = applicationUserRepository.findByUsername(user.getUsername());
        List<ApplicationUser> myfollowers = feed.getFollowers();
        model.addAttribute("allfollowers", myfollowers);
        return "feed";
    }
    @PostMapping("/follow")
    public RedirectView followUser(@AuthenticationPrincipal ApplicationUser user, @RequestParam Long id) {
        ApplicationUser feed = applicationUserRepository.findByUsername(user.getUsername());
        ApplicationUser follow = applicationUserRepository.findById(id).get();
        feed.getFollowers().add(follow);
        applicationUserRepository.save(feed);
        return new RedirectView("/feed");
    }


}