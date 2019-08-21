package com.example.blog.Controller;

import com.example.blog.Data.PostRepo;
import com.example.blog.Model.Create;
import com.example.blog.Model.Register;
import com.example.blog.Model.Register1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.rmi.MarshalledObject;

@Controller
@SessionAttributes("register")
@RequestMapping("/blog/edit/")
public class EditController {


    private PostRepo postRepo;
    @Autowired
    public EditController(PostRepo postRepo)
    {
        this.postRepo=postRepo;
    }

    @ModelAttribute(name="create")
    public Create create(){
        return new Create();
    }


    /*@GetMapping
    public String editPost(@SessionAttribute("register") Register register, Model model){
        Create user= (Create) postRepo.findAllByName(register.getName());
        model.addAttribute("user",user);
        return "edit";
    }*/

    @PostMapping(value = "/{id}")
    public String setChanges(@PathVariable String id,@Valid Create create,@SessionAttribute("register")Register register,Model model){
        Create user1= (Create) postRepo.findAllByNameAndId(register.getName(),id);
        user1.setMessage(create.getMessage());
        user1.setSummary(create.getSummary());
        user1.setTitle(create.getTitle());
        postRepo.save(user1);
        /*model.addAttribute("user",user1);*/
        return "redirect:/blog";
    }



}
