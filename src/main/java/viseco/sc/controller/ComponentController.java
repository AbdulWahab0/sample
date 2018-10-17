package viseco.sc.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import viseco.sc.model.document.Component;
import viseco.sc.model.repository.ComponentRespository;
import viseco.sc.service.ComponentService;

@Controller



public class ComponentController {
    private static final Logger logger = LoggerFactory.getLogger(ComponentController.class);
    @Autowired
    private ComponentService componentService;
    @Autowired
    ComponentRespository componentRespository;

    /* @GetMapping(value = "/createcomponent")
     public ModelAndView createComponentView() {
         ModelAndView modelAndView = new ModelAndView("addnewcomponent");


         modelAndView.addObject("component", new Component());
         *//*        modelAndView.addObject("allStatus", getStatus());*//*
        return modelAndView;


    }*/
    @GetMapping("/createcomponent")
    public String createComponentView(Model model) {
        model.addAttribute("component", new Component());
        return "addnewcomponent";
    }

    @PostMapping("/component/create")

    public String createComponent(@ModelAttribute Component component, BindingResult result) {
        if(component.getId() != null) {
            /*  Update user  */
            component.setId(component.getId());
            Optional<Component> post = componentRespository.findById(component.getId());
            component.setCreatedDate(post.get().getCreatedDate());
            component.setCreatedDate(component.getCreatedDate());

        } else {
            /*  Create && Set date for new user   */
            component.setCreatedDate(new Date());
        }
        System.out.println("dateeeeeeeeeeeee === "+ component.getCreatedDate());
        componentRespository.save(component);
        return "redirect:/components";
    }

    /* @GetMapping("/component/{id}/edit")
     public String edit(@PathVariable String id, Model model) {


      Component component=componentRespository.findById(id);
         model.addAttribute("componentlistedit", component);

         return "addnewcomponent";
     }*/
    @GetMapping("/component/{id}/edit")

    public String edit(@PathVariable String id, Model model) {

        Optional<Component> post = componentRespository.findById(id);
       /* System.out.println("date ========= "+post.get().getCreatedDate());
        System.out.println(post);*/
        model.addAttribute("component", post);

        return "addnewcomponent";
    }

    @GetMapping("/component/{id}/delete")
    public String delete(@PathVariable String id)

    {
        componentRespository.deleteById(id);

        return "redirect:/components";

    }
    @GetMapping("/component-category/{category_name}")
    public String filterComponentsByCategory(@PathVariable String category_name, Model model,Component component)

    {
        String categoryName = category_name;
        List<Component> componentList= componentRespository.findByCatageory(category_name);

        model.addAttribute("componentlist", componentList);

//


        return "components";

    }




}