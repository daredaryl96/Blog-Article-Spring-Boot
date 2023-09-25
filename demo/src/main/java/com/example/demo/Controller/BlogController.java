package com.example.demo.Controller;

import com.example.demo.Model.Post;
import com.example.demo.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts); //to figure out why "posts and post" matters
        return "index";
    }
}