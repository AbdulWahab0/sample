package viseco.sc.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import viseco.sc.helper.AjaxResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import viseco.sc.helper.Edge;
import viseco.sc.helper.GraphNode;
import viseco.sc.model.document.*;
import viseco.sc.model.domain.Component;
import viseco.sc.model.repository.*;
import viseco.sc.xmlconversion.ServiceGraph;
import viseco.sc.message.Response;
import java.io.IOException;
import java.io.InputStream;

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
    ComponentRepository componentRepository;
    //@Autowired
    //ServiceGraphInstanceRespository serviceGraphInstanceRespository;
    @Autowired
    NSDinfoRepository NsDinfoRepository;
    @Autowired
    VNFDInfoRepository vnfdInfoRepository;
    @Autowired
    GraphDeployvnfRepository graphDeployvnfRepository;
    @Autowired
    ServiceGraphsrRepository serviceGraphsrRepository;


    List<GraphDeploy> graphDeploys = new ArrayList<GraphDeploy>();

    /*@RequestMapping("/servicegraph")
    public String ServiceGraphcall(GraphInfo graphInfo) {
     return "application";
    }*/
    @RequestMapping("/graph")
    public String Graph(GraphInfo graphInfo) {
    return "graph";
    }
    @PostMapping("/savedeploygraph")
    public String readjasonfile() {
       // read json and write to db
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<NSDInfo>> typeReference = new TypeReference<List<NSDInfo>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/astridapp.json");
        int i = 0;
        try {
            List<NSDInfo> nsdInfos = mapper.readValue(inputStream, typeReference);
            NsDinfoRepository.saveAll(nsdInfos);
            List<NSDInfo> nsdInfoss = (List<NSDInfo>) NsDinfoRepository.findAll();
            List<VNFDInfo> vnfdInfossss = new ArrayList<VNFDInfo>();
            vnfdInfossss = nsdInfoss.get(3).getVnfd();
            vnfdInfoRepository.saveAll(vnfdInfossss);
            System.out.println("graph Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save graph: " + e.getMessage());
        }
        return "redirect:/components";
    }
    /*@RequestMapping("/graphdeploy")
    public String Graphdeploy(Model model) {

        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();
        List<viseco.sc.helper.Edge> edges = new ArrayList<>();
        List<viseco.sc.model.domain.Component> componentList = componentRepository.findAll();
        //String id=componentList.get(0).getId();
        //List<Edge> edges = new ArrayList<>();

        List<GraphInfo> graph = serviceGraphRepository.findAll();
        String xmlData = graph.get(0).getXml();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ServiceGraph.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlData);
            serviceGraph = (ServiceGraph) unmarshaller.unmarshal(reader);
            List<viseco.sc.model.domain.Component> component = componentRepository.findAll();
            int j = 0;

            for (viseco.sc.xmlconversion.GraphNode node : serviceGraph.getGraphNodeDescriptor().getGraphNodes()) {
                if(node.getNodeId()!=null) {
                    if (node.getNodeId().equals(component.get(j).getId())) {

                        // model.addAttribute("componentlist", component.get(j).getName());

                        model.addAttribute("componentlist", componentRepository.findAll());

                    }
                }
                j++;
            }


        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return "deploygraph";
}*/

    @PostMapping("/deploy")

    public String savegraph(@ModelAttribute GraphDeploy graphDeploy, BindingResult result) {

        graphDeployvnfRepository.save(graphDeploy);
        return "redirect:/application";
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


    @RequestMapping("/Service")
    public String graph(Model model) {

        model.addAttribute("graphlist", serviceGraphsrRepository.findAll());
        return "components";
    }


    /*@RequestMapping("/edit-template-descriptor")
    public ResponseEntity<AjaxResponse> edittemplateDescriptor(GraphInfo graphInfo) {
        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();

        List<viseco.sc.helper.Edge> edges = new ArrayList<>();
        List<viseco.sc.model.domain.Component> componentList = componentRepository.findAll();

        //String id=componentList.get(0).getId();
        //List<Edge> edges = new ArrayList<>();

        List<GraphInfo> graph = serviceGraphRepository.findAll();
        String xmlData = graph.get(0).getXml();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ServiceGraph.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlData);
            serviceGraph = (ServiceGraph) unmarshaller.unmarshal(reader);

            ObjectMapper mapperObj = new ObjectMapper();

            // get serviceGraph object as a json string
            String json = mapperObj.writeValueAsString(serviceGraph);

            //serviceGraphRepository.saveAll(json);


            List<viseco.sc.model.domain.Component> component = componentRepository.findAll();
            int j = 0;

            for (viseco.sc.xmlconversion.GraphNode node : serviceGraph.getGraphNodeDescriptor().getGraphNodes()) {
                if(node.getNodeId()!=null) {

                    if (node.getNodeId().equals(component.get(j).getId())) {

                        viseco.sc.helper.GraphNode newNode = new viseco.sc.helper.GraphNode(node.getNodeId(), component.get(j).getUserName());

                        nodes.add(newNode);


                        viseco.sc.helper.Edge edge = new viseco.sc.helper.Edge(node.getNodeId(), component.get(1).getId(), "");
                        if (!node.getNodeId().equals(component.get(1).getId())) {
                            edges.add(edge);
                        }


                    }
                }
                j++;
            }

            //edges.add(edge);[
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return ResponseEntity.ok(new AjaxResponse("Template Graph", nodes, edges));


    }
*/


    /*@RequestMapping("/create-template-descriptor_newNode")
    public ResponseEntity<AjaxResponse> creattemplateDescriptornodejson(GraphInfo graphInfo) {
        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();

        List<viseco.sc.helper.Edge> edges = new ArrayList<>();
        List<VNFDInfo> vnfnodes = new ArrayList<>();

            for (NSDInfo nsdInfoss : (List<NSDInfo>) NsDinfoRepository.findAll()) {
                nodes.add(new GraphNode(nsdInfoss.getVnfd().get(0).getId(), nsdInfoss.getVnfd().get(0).getName()));
                //edges.add(new Edge(nsdInfoss.getVnf_dependency(), "723bdcd8-d078-4862-988f-9932c5b6c010", "abdul"));
            }



       *//* nodes.add(new GraphNode("723bdcd8-d078-4862-988f-9932c5b6c009", "NewNode"));
        nodes.add(new GraphNode("723bdcd8-d078-4862-988f-9932c5b6c010", "NewNodetwo"));


        edges.add(new Edge("723bdcd8-d078-4862-988f-9932c5b6c009","723bdcd8-d078-4862-988f-9932c5b6c010","abdul"));
        edges.add(new Edge("723bdcd8-d078-4862-988f-9932c5b6c010","circle-5bb6236b716616bd22fd5035","paara"))*//*
        //
*//*       for (int i = 0; i <nodes.size(); i++)
           GraphNode a = nodes.get(i);*//*


        return ResponseEntity.ok(new AjaxResponse("Template Graph", nodes, edges));


    }*/
   /* @RequestMapping("/service-graph")
    public ResponseEntity<AjaxResponse> getServiceGraphData(GraphInfo graphInfo) {
        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();

        List<viseco.sc.helper.Edge> edges = new ArrayList<>();
        List<viseco.sc.model.domain.Component> componentList = componentRepository.findAll();

        //String id=componentList.get(0).getId();
        //List<Edge> edges = new ArrayList<>();

        List<GraphInfo> graph = serviceGraphRepository.findAll();
        String xmlData = graph.get(0).getXml();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ServiceGraph.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlData);
            serviceGraph = (ServiceGraph) unmarshaller.unmarshal(reader);

            ObjectMapper mapperObj = new ObjectMapper();

            // get serviceGraph object as a json string
            String json = mapperObj.writeValueAsString(serviceGraph);

            //serviceGraphRepository.saveAll(json);


            List<viseco.sc.model.domain.Component> component = componentRepository.findAll();
            int j = 0;

            for (viseco.sc.xmlconversion.GraphNode node : serviceGraph.getGraphNodeDescriptor().getGraphNodes()) {
                if(node.getNodeId()!=null) {

                    if (node.getNodeId().equals(component.get(j).getId())) {

                        viseco.sc.helper.GraphNode newNode = new viseco.sc.helper.GraphNode(node.getNodeId(), component.get(j).getUserName());

                        nodes.add(newNode);


                        viseco.sc.helper.Edge edge = new viseco.sc.helper.Edge(node.getNodeId(), component.get(1).getId(), "");
                        if (!node.getNodeId().equals(component.get(1).getId())) {
                            edges.add(edge);
                        }


                    }
                }
                j++;
            }

            //edges.add(edge);[
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return ResponseEntity.ok(new AjaxResponse("Template Graph", nodes, edges));
}

    @RequestMapping("/instance-graph")
    public ResponseEntity<AjaxResponse> getServiceInsatnceGraphData(GraphInfo graphInfo) {
        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();
        List<viseco.sc.helper.Edge> edges = new ArrayList<>();
        List<viseco.sc.model.domain.Component> componentList = componentRepository.findAll();
        List<GraphInfo> graph = serviceGraphRepository.findAll();
        String xmlData = graph.get(0).getXml();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ServiceGraph.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlData);
            serviceGraph = (ServiceGraph) unmarshaller.unmarshal(reader);
            ObjectMapper mapperObj = new ObjectMapper();
            String json = mapperObj.writeValueAsString(serviceGraph);
            List<viseco.sc.model.domain.Component> component = componentRepository.findAll();
            int j = 0;

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }


        nodes.add(new GraphNode("5bb6235d716616bd22fd5035", "ping"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5036", "pong"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5037", "secgw"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5038", "newNode1"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5039", "newnode2"));
        edges.add(new Edge("5bb6235d716616bd22fd5035","5bb6235d716616bd22fd5037",""));
        edges.add(new Edge("5bb6235d716616bd22fd5037","5bb6235d716616bd22fd5036",""));
        return ResponseEntity.ok(new AjaxResponse("data", nodes, edges));


    }*/

    @RequestMapping("/newapplication-graph")
    public ResponseEntity<AjaxResponse> CreatNewApplicatiion (viseco.sc.model.domain.Component component) {
        viseco.sc.model.document.Component comp=null;
        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();
        List<viseco.sc.helper.Edge> edges = new ArrayList<>();
        /*List<Component> componentList = componentRepository.findAll();*/


            for (viseco.sc.model.domain.Component componentList : componentRepository.findAll()) {

                String xmlData = componentList.getXml();
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(viseco.sc.model.document.Component.class);
                    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                    StringReader reader = new StringReader(xmlData);
                    comp = (viseco.sc.model.document.Component) unmarshaller.unmarshal(reader);
                   /* nodes.add(new GraphNode(comp.getDescriptiveCNMetadata().getCNID(), comp.getDescriptiveCNMetadata().getCNName()));*/
                    nodes.add(new GraphNode("5bb6235d716616bd22fd5036", "pongggg"));
                    nodes.add(new GraphNode("5bb6235d716616bd22fd5037", "secgw"));
                    nodes.add(new GraphNode("5bb6235d716616bd22fd5038", "newNode1"));
                    nodes.add(new GraphNode("5bb6235d716616bd22fd5039", "newnode2"));



                } catch (JAXBException e) {
                    e.printStackTrace();
                }

            }


        /*nodes.add(new GraphNode("5bb6235d716616bd22fd5035", "pingsss"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5036", "pongggg"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5037", "secgw"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5038", "newNode1"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5039", "newnode2"));
        edges.add(new Edge("5bb6235d716616bd22fd5035","5bb6235d716616bd22fd5037",""));
        edges.add(new Edge("5bb6235d716616bd22fd5037","5bb6235d716616bd22fd5036",""));*/
        return ResponseEntity.ok(new AjaxResponse("data", nodes, edges));


    }
    @PostMapping("/getcomponent")
    public String getcomponents (@ModelAttribute Component component, BindingResult result) {
        viseco.sc.model.document.Component comp=null;
        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();
        List<viseco.sc.helper.Edge> edges = new ArrayList<>();
        /*List<Component> componentList = componentRepository.findAll();*/


        for (viseco.sc.model.domain.Component componentList : componentRepository.findAll()) {

            String xmlData = componentList.getXml();
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(viseco.sc.model.document.Component.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                StringReader reader = new StringReader(xmlData);
                comp = (viseco.sc.model.document.Component) unmarshaller.unmarshal(reader);
                nodes.add(new GraphNode(comp.getDescriptiveCNMetadata().getCNID(), comp.getDescriptiveCNMetadata().getCNName()));

            } catch (JAXBException e) {
                e.printStackTrace();
            }

        }


        /*nodes.add(new GraphNode("5bb6235d716616bd22fd5035", "pingsss"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5036", "pongggg"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5037", "secgw"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5038", "newNode1"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5039", "newnode2"));
        edges.add(new Edge("5bb6235d716616bd22fd5035","5bb6235d716616bd22fd5037",""));
        edges.add(new Edge("5bb6235d716616bd22fd5037","5bb6235d716616bd22fd5036",""));*/
        return "components";


    }

}


