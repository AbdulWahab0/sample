package viseco.sc.controller;

import com.sun.tools.classfile.Dependency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import viseco.sc.helper.AjaxResponse;
import viseco.sc.helper.Edge;
import viseco.sc.helper.GraphNode;
import viseco.sc.model.document.Component;
import viseco.sc.model.document.GraphDependency;
import viseco.sc.model.document.GraphDeploy;
import viseco.sc.model.document.GraphInfo;
import viseco.sc.model.repository.ComponentRespository;
import viseco.sc.model.repository.ServiceGraphRepository;
import viseco.sc.xmlconversion.ServiceGraph;
import viseco.sc.message.Response;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller


public class ServiceGraphController {
    private static final Logger logger = LoggerFactory.getLogger(GraphInfo.class);
    @Autowired
    ServiceGraphRepository serviceGraphRepository;
    @Autowired
    ComponentRespository componentRespository;

    List<GraphDeploy> graphDeploys=new ArrayList<GraphDeploy>();

    @RequestMapping("/servicegraph")
    public  String ServiceGraphcall(GraphInfo graphInfo)

    {
        return "application";

    }
    @RequestMapping("/graph")
    public  String Graph(GraphInfo graphInfo)

    {
    return "graph";

    }
    @RequestMapping("/graphdeploy")
    public  String Graphdeploy(Model model)

    {

        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();
        List<viseco.sc.helper.Edge> edges = new ArrayList<>();
        List<Component> componentList=componentRespository.findAll();
        //String id=componentList.get(0).getId();
        //List<Edge> edges = new ArrayList<>();

        List<GraphInfo> graph = serviceGraphRepository.findAll();
        String xmlData = graph.get(0).getXml();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ServiceGraph.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlData);
            serviceGraph = (ServiceGraph) unmarshaller.unmarshal(reader);
            List<Component> component = componentRespository.findAll();
            int j = 0;

            for (viseco.sc.xmlconversion.GraphNode node : serviceGraph.getGraphNodeDescriptor().getGraphNodes()) {
                if (node.getNid().equals(component.get(j).getId())) {

                   // model.addAttribute("componentlist", component.get(j).getName());

                    model.addAttribute("componentlist",componentRespository.findAll());

                }
                j++;
            }


        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return "deploygraph";


    }

    @PostMapping("/deploy")

    public String savegraph(@ModelAttribute Component component, BindingResult result) {

        componentRespository.save(component);
        return "redirect:/components";
    }
    @GetMapping(value = "/all")
    public Response getResource() {
        Response response = new Response("Done", graphDeploys);

        return response;
    }
    @PostMapping(value = "/save")
    public Response postGraph(@RequestBody GraphDeploy graphDeploy) {
        graphDeploys.add(graphDeploy);

        Response response = new Response("Done", graphDeploy);
        return response;
    }


   @RequestMapping("/service-graph")
   public ResponseEntity<AjaxResponse> getServiceGraphData(GraphInfo graphInfo) {
       ServiceGraph serviceGraph = null;
       List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();
       List<viseco.sc.helper.Edge> edges = new ArrayList<>();
       List<Component> componentList=componentRespository.findAll();
       //String id=componentList.get(0).getId();
       //List<Edge> edges = new ArrayList<>();

       List<GraphInfo> graph = serviceGraphRepository.findAll();
       String xmlData = graph.get(0).getXml();
       try {
           JAXBContext jaxbContext = JAXBContext.newInstance(ServiceGraph.class);
           Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
           StringReader reader = new StringReader(xmlData);
           serviceGraph = (ServiceGraph) unmarshaller.unmarshal(reader);

           List<Component> component = componentRespository.findAll();
           int j = 0;

           for (viseco.sc.xmlconversion.GraphNode node : serviceGraph.getGraphNodeDescriptor().getGraphNodes()) {
               if (node.getNid().equals(component.get(j).getId())) {
                   if (node.getGraphDependencies() == null) {
                       viseco.sc.helper.GraphNode newNode = new viseco.sc.helper.GraphNode(node.getNid(), component.get(j).getName());
                       nodes.add(newNode);
                   } else {
                       viseco.sc.helper.GraphNode newNode = new viseco.sc.helper.GraphNode(node.getNid(), component.get(j).getName());
                       nodes.add(newNode);
                       for (viseco.sc.xmlconversion.GraphDependency Dep : node.getGraphDependencies()) {
                           viseco.sc.helper.Edge edge = new viseco.sc.helper.Edge(component.get(j).getId(), Dep.getNid(), "Require");
                           edges.add(edge);
                       }
                   }
               }
               j++;
           }
       } catch (JAXBException e) {
           e.printStackTrace();
       }
       return ResponseEntity.ok(new AjaxResponse("test Graph", nodes, edges));


   }

    }

