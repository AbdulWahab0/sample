package viseco.sc.controller;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*import org.openbaton.sdk.NFVORequestor;
import org.openbaton.sdk.api.rest.NetworkServiceDescriptorAgent;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import viseco.sc.model.document.*;
import viseco.sc.model.document.VNFDInfo;
import org.openbaton.catalogue.mano.descriptor.NetworkServiceDescriptor;
import org.openbaton.sdk.NfvoRequestorBuilder;






import viseco.sc.model.repository.*;


import viseco.sc.service.ComponentService;

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
    @Autowired
    TestRepo testRepo;
    @Autowired
    NSDinfoRepository nsDinfoRepository;
    @Autowired
    VNFDInfoRepository vnfdInfoRepository;



    @GetMapping("/editcomponent")
    public String createComponentView(Model model) {

        model.addAttribute("component", new Component());


        return "addnewcomponent";
    }

        @PostMapping("/component/create")
        public String createComponent(@ModelAttribute test test, BindingResult result) {
            viseco.sc.model.document.test test1=new test();


            testRepo.save(test);
        return "redirect:/components";
    }












    @GetMapping("/vnf-components")
  public String getVnfComponentsList(NSDInfo nsdInfo,Model model)
  {
      for (viseco.sc.model.document.NSDInfo nsdInfo1 : nsDinfoRepository.findAll()) {

          for (VNFDInfo vnfdInfo : nsdInfo1.getVnfd()) {



              model.addAttribute("vnfcomponentlist", vnfdInfo);
          }
          }
      return "vnfComponents";
}


    @RequestMapping(value = "/vnfcomponent-details/{id}", method = RequestMethod.GET)
    public String vnfcomponentdetails(VNFDInfo vnfdInfo,Model model, @PathVariable("id") String id) {



      //List<VNFDInfo> vnfdInfo1 =  vnfdInfoRepository.findByid(id);
        //Optional<VNFDInfo> vnfdInfo1 =  vnfdInfoRepository.findById(id);
        //NetworkServiceDescriptorAgent nfvoRequestor;

        //List<NetworkServiceDescriptorAgent> nfvoRequestor1 =nfvoRequestor.getNetworkServiceDescriptorAgent().findAll();




        return "componentdetalis";
    }

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
                    //comp.getExposedChainableEndpoints().getExposedChainableEndpoint().;
                    /*for (viseco.sc.model.domain.ChainableEndpointCategory endpoint : chainableEndpointCategoryRepository.findAll()) {
                        model.addAttribute("endpoints", endpoints.get(0).getChainableEndpointCategory().getCepcid());


                    }*/
                    //model.addAttribute("EndpointsId", comp.getExposedChainableEndpoints().getExposedChainableEndpoint().get(1).getChainableEndpointCategory().getCepcid());
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
            //model.addAttribute("EndpointsId", comp.getExposedChainableEndpoints().getExposedChainableEndpoint().get(1).getECEPID());


            model.addAttribute("CNMetrics", comp.getCNMetrics().getCNMetric());






            j++;
        }
        return "componentdetalis";
    }

}