package viseco.sc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import viseco.sc.model.document.Component;
import viseco.sc.model.document.GraphInfo;

import viseco.sc.model.repository.ServiceGraphRepository;
import viseco.sc.xmlconversion.ServiceGraph;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

/*@Autowired
    ComponentRespository componentRespository;*/
    private static final Logger logger = LoggerFactory.getLogger(GraphInfo.class);
    @Autowired
    ServiceGraphRepository serviceGraphRepository;
    @RequestMapping("/")
    public String dashboard(Map<String, Object> model) {
    return "dashboard";
    }
    @RequestMapping("/application")
    public  String ServiceGraphcall(Model model)
    {
        model.addAttribute("servicegraph", serviceGraphRepository.findAll());
        return "application";
    }
    @RequestMapping("/templatedescriptors")
    public  String templateDescriptors(Model model)
    {
        model.addAttribute("servicegraph", serviceGraphRepository.findAll());
        return "templatedescriptors";
    }
    @RequestMapping("/edittemplatedescriptor")
    public  String edittemplateDescriptors(Model model)
    {
     return "edittemplatedescriptor";
    }
    @RequestMapping("/templateGraph")
    public  String templateGraph(Model model)
    {
      return "templategraph";
    }
    @RequestMapping("/instancerecords")
    public  String InsatnceGraphcall(Model model)
    {
      model.addAttribute("servicegraph", serviceGraphRepository.findAll());
      return "instancerecords";
    }
    /*@RequestMapping("/instancegraph")
    public  String instanceGraph(Model model)
    {
        return "instancegraph";
    }*/
    /*@RequestMapping("/components")
    public String components(Model model) {
        model.addAttribute("componentlist", componentRespository.findAll());
        return "components";
    }*/
    @RequestMapping("/resource")
    public String resource(Map<String, Object> model) {
        return "resource";
    }
  @RequestMapping("/activity")
    public String activity(Map<String, Object> model) {
     return "activity";
    }
    @RequestMapping("/account")
    public String account(Map<String, Object> model) {
    return "account";
    }
    @RequestMapping("/policymanagement")
    public String policymanagement(Map<String, Object> model) {
    return "policymanagement";
    }
    @RequestMapping("/addnewtemplate")
    public String applicationTemplate(Map<String, Object> model) {
    return "addnewtemplate";
    }
}