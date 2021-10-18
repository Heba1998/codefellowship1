package com.lab.codefellowship1.controller;

import com.lab.codefellowship1.models.ApplicationUser;
import com.lab.codefellowship1.models.Post;
import com.lab.codefellowship1.repository.ApplicationUserRepository;
import com.lab.codefellowship1.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PostController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    public String getPostForm(Model model, Principal principal) {
        model.addAttribute("user", principal);
        return "post";
    }

    @PostMapping("/posts")
    public RedirectView createPost(String body, Principal principal, Model model) {
        ApplicationUser logUser = applicationUserRepository.findByUsername(principal.getName());
        Post post = new Post(body, logUser);
        postRepository.save(post);
        return new RedirectView("/users/" + logUser.getId());
    }
}


