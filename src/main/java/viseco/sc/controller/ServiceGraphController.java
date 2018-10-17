package viseco.sc.controller;

import com.sun.tools.classfile.Dependency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import viseco.sc.helper.AjaxResponse;
import viseco.sc.helper.Edge;
import viseco.sc.helper.GraphNode;
import viseco.sc.model.document.Component;
import viseco.sc.model.document.GraphDependency;
import viseco.sc.model.document.GraphInfo;
import viseco.sc.model.repository.ComponentRespository;
import viseco.sc.model.repository.ServiceGraphRepository;
import viseco.sc.xmlconversion.ServiceGraph;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ServiceGraphController {
    private static final Logger logger = LoggerFactory.getLogger(GraphInfo.class);
    @Autowired
    ServiceGraphRepository serviceGraphRepository;
    @Autowired
    ComponentRespository componentRespository;

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
    public  String Graphdeploy(GraphInfo graphInfo)

    {
        return "deploygraph";

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
            int i = 0;
            JAXBContext jaxbContext = JAXBContext.newInstance(ServiceGraph.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlData);
            serviceGraph = (ServiceGraph) unmarshaller.unmarshal(reader);

            for (viseco.sc.xmlconversion.GraphNode node : serviceGraph.getGraphNodeDescriptor().getGraphNodes()) {
                //List<Dependency> dependencies =serviceGraph.getGraphNodeDescriptor().getGraphNodes().get(0).getGraphDependencies()

                List<Component> component = componentRespository.findAll();



                //for (Component component:componentRespository.findAll()) {
                    if (node.getGraphDependencies() == null) {
                        //if (node.getNid().equals(component.getId())) {
                        viseco.sc.helper.GraphNode newNode = new viseco.sc.helper.GraphNode(node.getNid(),component.get(1).getName());
                        nodes.add(newNode);
                    } else {


                        viseco.sc.helper.GraphNode newNode = new viseco.sc.helper.GraphNode(node.getNid(),component.get(1).getName() );
                        nodes.add(newNode);
                        for (viseco.sc.xmlconversion.GraphDependency Dep : node.getGraphDependencies()) {


                            viseco.sc.helper.Edge edge = new viseco.sc.helper.Edge(component.get(1).getId(), Dep.getNid(), "Require");


                            //Edge edge = new Edge(i, 0, "Require");

                            edges.add(edge);


                            //}
                            //i++;

                        }
                    //}
                }
            }








        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new AjaxResponse("test Graph", nodes, edges));


    }

    }
