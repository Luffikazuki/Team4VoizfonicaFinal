package com.example.blog.Controller;

import com.example.blog.Data.PostRepo;
import com.example.blog.Model.Create;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    private PostRepo postRepo;
    @Autowired
    public IndexController(PostRepo postRepo)
    {
        this.postRepo=postRepo;
    }
    @GetMapping
    public String display(Model model)
    {
        List<Create> plans = new ArrayList<>();
        postRepo.findAll().forEach(i -> plans.add(i));
        model.addAttribute("plans",plans);
        return "index";
    }

}
