package viseco.sc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import viseco.sc.model.document.Component;
import viseco.sc.model.repository.ComponentRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Controller
public class ApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
    @Autowired
    ComponentRepository componentRepository;
    /*@GetMapping("/createapplication")
    public  String addapplication(Model model)
    {

        return "addnewapplication";
    }*/


    @RequestMapping("/createapplication")
    public  String getComponent(viseco.sc.model.domain.Component component,Model model) {
        Component comp = null;
        //int j = 0;
        for (viseco.sc.model.domain.Component componentList: componentRepository.findAll())
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
            //j++;
        }
        model.addAttribute("componentlist", comp.getDescriptiveCNMetadata());
        return "addnewapplication";
    }
}
