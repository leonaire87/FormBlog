package be.ehb.demo.controllers;

import be.ehb.demo.model.Blog;
import be.ehb.demo.model.BlogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    BlogDAO dao;

    @ModelAttribute(value = "allePosts")
    public Iterable<Blog> getAllPosts(){return dao.findAll();}

    @ModelAttribute(value = "nieuwePosts")
    public Blog postsToSave(){
        return new Blog();
    }


    @RequestMapping(value = {"", "/","/index"}, method = RequestMethod.GET)
    public String  showIndex(ModelMap map){
        return "index";
    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.POST)
    public String saveBlog(@ModelAttribute("nieuwePosts") @Valid Blog nieuwePosts, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "index";
        dao.save(nieuwePosts);
        return "redirect:/index";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBlog(@PathVariable(value = "id") int id) {
        dao.deleteById(id);
        return "redirect:/index";
    }


}
