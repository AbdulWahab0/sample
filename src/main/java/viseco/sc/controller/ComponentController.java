package viseco.sc.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import viseco.sc.helper.AjaxResponse;
import viseco.sc.helper.Edge;
import viseco.sc.helper.GraphNode;
import viseco.sc.model.document.*;

import viseco.sc.model.repository.ChainableEndpointCategoryRepository;
import viseco.sc.model.repository.ComponentRepository;
import viseco.sc.model.repository.ServiceGraphsrRepository;
import viseco.sc.service.ComponentService;
import viseco.sc.xmlconversion.ServiceGraph;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Controller
public class ComponentController {
    private static final Logger logger = LoggerFactory.getLogger(ComponentController.class);
    @Autowired
    private ComponentService componentService;

    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    ChainableEndpointCategoryRepository chainableEndpointCategoryRepository;

    /*@GetMapping("/createcomponent")
    public String createComponentView(Model model) {
        model.addAttribute("component", new Component());
        return "addnewcomponent";
    }

    *//*@PostMapping("/component/create")
    public String createComponent(@ModelAttribute Component component, BindingResult result) {
        if(component.getId() != null) {
            *//**//*  Update user  *//**//*
            component.setId(component.getId());
            Optional<Component> post = componentRespository.findById(component.getId());
            component.setCreatedDate(post.get().getCreatedDate());
            component.setCreatedDate(component.getCreatedDate());

        } else {
            *//**//*  Create && Set date for new user   *//**//*
            component.setCreatedDate(new Date());
        }
        System.out.println("dateeeeeeeeeeeee === "+ component.getCreatedDate());
        componentRespository.save(component);
        return "redirect:/components";
    }*//*

    @GetMapping("/component/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        Optional<Component> post = componentRespository.findById(id);
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
        return "components";
    }*/
   /* @RequestMapping("/components")
    public String components(Model model) {
        model.addAttribute("componentlist", componentRespository.findAll());
        return "components";
    }*/
    @RequestMapping("/components")
    public  String getComponent(viseco.sc.model.domain.Component component,Model model) {
        Component comp = null;
        List<Component> nodes = new ArrayList<>();
        List<DescriptiveCNMetadata> usingComponent = new ArrayList<>();
        List<ExposedChainableEndpoint> endpoints = new ArrayList<>();
        int i = 0;

            for (viseco.sc.model.domain.Component componentList : componentRepository.findAll()) {
                String xmlData = componentList.getXml();
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(Component.class);
                    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                    StringReader reader = new StringReader(xmlData);
                    comp = (Component) unmarshaller.unmarshal(reader);
                    //comp.getExposedChainableEndpoints().getExposedChainableEndpoint().get(1).getECEPID();

                    //model.addAttribute("endpoints", chainableEndpointCategoryRepository.findAll());

                    usingComponent.add(comp.getDescriptiveCNMetadata());


                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                model.addAttribute("componentlist", usingComponent);
                //model.addAttribute("comp", comp);
                //j++;
            }



        return "components";
    }


    @RequestMapping(value = "/component-details/{id}", method = RequestMethod.GET)
    public String componentdetails(viseco.sc.model.domain.Component component,Model model, @PathVariable("id") String id) {

        Component comp = null;


    int j = 0;
      viseco.sc.model.domain.Component componentList= componentRepository.findByCnid(id);
        {

            String xmlData = componentList.getXml();

            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Component.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                StringReader reader = new StringReader(xmlData);
                comp = (Component) unmarshaller.unmarshal(reader);



            } catch (JAXBException e) {
                e.printStackTrace();
            }

            model.addAttribute("componentdetaillist", comp.getDescriptiveCNMetadata());
            model.addAttribute("BehavioralProfile", comp.getBehavioralProfile());
            model.addAttribute("ExecutionRequirements", comp.getExecutionRequirements());
            model.addAttribute("Configuration", comp.getConfigurationParameters().getConfigurationParameter());
            //model.addAttribute("EndpointsId", comp.getExposedChainableEndpoints().getExposedChainableEndpoint().get(1).getChainableEndpointCategory());


            model.addAttribute("CNMetrics", comp.getCNMetrics().getCNMetric());






            j++;
        }
        return "componentdetalis";
    }

}