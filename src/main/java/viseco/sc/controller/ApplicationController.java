package viseco.sc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import viseco.sc.helper.AjaxResponse;
import viseco.sc.helper.GraphNode;
import viseco.sc.model.document.Component;
import viseco.sc.model.document.GraphInfo;
import viseco.sc.model.document.NSDInfo;
import viseco.sc.model.document.VNFDInfo;
import viseco.sc.model.repository.ComponentRepository;
import viseco.sc.model.repository.GraphinfoRepository;
import viseco.sc.model.repository.NSDinfoRepository;
import viseco.sc.model.repository.VNFDInfoRepository;
import viseco.sc.xmlconversion.ServiceGraph;

import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    NSDinfoRepository NsDinfoRepository;
    @Autowired
    VNFDInfoRepository vnfdInfoRepository;
    @Autowired
    GraphinfoRepository graphinfoRepository;


    @RequestMapping("/createapplication")
    public  String getComponent(VNFDInfo vnfdInfo, Model model) {
        List<VNFDInfo> vnfdInfoList= new ArrayList<>();
        NSDInfo wrapper = new NSDInfo();
        GraphInfo graphInfo = new GraphInfo();


        int j = 0;
        /*List<NSDInfo> nsdInfoss = (List<NSDInfo>) NsDinfoRepository.findAll();

        for(VNFDInfo vnfdInfo1:nsdInfoss.get(0).getVnfd()) {
}*/

        for(NSDInfo vnfdInfo1:NsDinfoRepository.findAll()) {




            model.addAttribute("Vnflist",vnfdInfo1);
        }
        for(GraphInfo graphInfo1:graphinfoRepository.findAll()) {




            model.addAttribute("acradiaapplist",graphInfo1);
        }

        return "addnewapplication";
    }
   /* @RequestMapping("/createapplication")
    public  String getlistfarcadiagraph(GraphInfo graphInfo, Model model) {
        List<VNFDInfo> vnfdInfoList= new ArrayList<>();
        GraphInfo wrapper = new GraphInfo();


        int j = 0;
        *//*List<NSDInfo> nsdInfoss = (List<NSDInfo>) NsDinfoRepository.findAll();

        for(VNFDInfo vnfdInfo1:nsdInfoss.get(0).getVnfd()) {
}*//*

        for(GraphInfo graphInfo1:graphinfoRepository.findAll()) {




            model.addAttribute("acradiaapplist",graphInfo1);
        }

        return "addnewapplication";
    }*/

  /*  @RequestMapping(value = "/component/{id}", method = RequestMethod.POST)
    public String createComponent(Model model, @PathVariable("id") String id) {

        Optional<NSDInfo> nsdInfot= NsDinfoRepository.findById(id);
        nsdInfot.get(0).


        return "redirect:/components";
    }*/





}




