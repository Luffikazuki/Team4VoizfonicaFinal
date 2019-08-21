package com.example.blog.Controller;

import com.example.blog.Data.PostRepo;
import com.example.blog.Model.Create;
import com.example.blog.Model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("register")
@RequestMapping("/delete")
public class DeleteController {

    private PostRepo postRepo;
    @Autowired
    public DeleteController(PostRepo postRepo)
    {
        this.postRepo=postRepo;
    }


    @GetMapping
    public String deletePost(@SessionAttribute("register")Register register){
    postRepo.deleteByName(register.getName());
    return "delete";
    }


}
