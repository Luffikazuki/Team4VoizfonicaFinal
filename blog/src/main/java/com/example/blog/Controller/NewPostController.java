package com.example.blog.Controller;

import com.example.blog.Data.PostRepo;
import com.example.blog.Model.Create;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newpost")
public class NewPostController {
    private PostRepo postRepo;
    @Autowired
    public NewPostController(PostRepo postRepo)
    {
        this.postRepo=postRepo;
    }
    @GetMapping
    public String print(Model model)
    {   model.addAttribute("create",new Create());
        return "newpost";
    }
    @PostMapping
    public String display(Create create,Model model)
    {
        postRepo.save(create);
        model.addAttribute("postSuccess", "yes");
        return "newpost";
    }
}
