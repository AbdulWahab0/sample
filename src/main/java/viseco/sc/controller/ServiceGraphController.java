package viseco.sc.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sun.tools.classfile.Dependency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    ServiceGraphInstanceRespository serviceGraphInstanceRespository;
    @Autowired
    NSDinfoRepository NsDinfoRepository;
    @Autowired
    VNFDInfoRepository vnfdInfoRepository;
    @Autowired
    GraphDeployvnfRepository graphDeployvnfRepository;
    @Autowired
    ServiceGraphsrRepository serviceGraphsrRepository;


    List<GraphDeploy> graphDeploys = new ArrayList<GraphDeploy>();

    @RequestMapping("/servicegraph")
    public String ServiceGraphcall(GraphInfo graphInfo) {
        return "application";

    }

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

    @RequestMapping("/graphdeploy")
    public String Graphdeploy(Model model) {

        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();
        List<viseco.sc.helper.Edge> edges = new ArrayList<>();
        List<Component> componentList = componentRespository.findAll();
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
                if(node.getNodeId()!=null) {
                    if (node.getNodeId().equals(component.get(j).getId())) {

                        // model.addAttribute("componentlist", component.get(j).getName());

                        model.addAttribute("componentlist", componentRespository.findAll());

                    }
                }
                j++;
            }


        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return "deploygraph";


    }

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


    @RequestMapping("/service-graph")
    public ResponseEntity<AjaxResponse> getServiceGraphData(GraphInfo graphInfo) {
        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();

        List<viseco.sc.helper.Edge> edges = new ArrayList<>();
        List<Component> componentList = componentRespository.findAll();

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


            List<Component> component = componentRespository.findAll();
            int j = 0;

            for (viseco.sc.xmlconversion.GraphNode node : serviceGraph.getGraphNodeDescriptor().getGraphNodes()) {
                if(node.getNodeId()!=null) {

                    if (node.getNodeId().equals(component.get(j).getId())) {

                        viseco.sc.helper.GraphNode newNode = new viseco.sc.helper.GraphNode(node.getNodeId(), component.get(j).getName());

                        nodes.add(newNode);


                        viseco.sc.helper.Edge edge = new viseco.sc.helper.Edge(node.getNodeId(), component.get(1).getId(), "http");
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


       /* nodes.add(new GraphNode("723bdcd8-d078-4862-988f-9932c5b6c009", "NewNode"));
        nodes.add(new GraphNode("723bdcd8-d078-4862-988f-9932c5b6c010", "NewNodetwo"));


        edges.add(new Edge("723bdcd8-d078-4862-988f-9932c5b6c009","723bdcd8-d078-4862-988f-9932c5b6c010","abdul"));
        edges.add(new Edge("723bdcd8-d078-4862-988f-9932c5b6c010","circle-5bb6236b716616bd22fd5035","paara"))*/;
        //
/*       for (int i = 0; i <nodes.size(); i++)
           GraphNode a = nodes.get(i);*/


        return ResponseEntity.ok(new AjaxResponse("Template Graph", nodes, edges));


    }
    @RequestMapping("/instance-graph")
    public ResponseEntity<AjaxResponse> getServiceInsatnceGraphData(GraphInfo graphInfo) {
        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();

        List<viseco.sc.helper.Edge> edges = new ArrayList<>();
        List<Component> componentList = componentRespository.findAll();

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


            List<Component> component = componentRespository.findAll();
            int j = 0;

            /*for (viseco.sc.xmlconversion.GraphNode node : serviceGraph.getGraphNodeDescriptor().getGraphNodes()) {
                if(node.getNodeId()!=null) {

                    if (node.getNodeId().equals(component.get(j).getId())) {

                        viseco.sc.helper.GraphNode newNode = new viseco.sc.helper.GraphNode(node.getNodeId(), component.get(j).getName());

                        nodes.add(newNode);


                        viseco.sc.helper.Edge edge = new viseco.sc.helper.Edge(node.getNodeId(), component.get(1).getId(), "http");
                        if (!node.getNodeId().equals(component.get(1).getId())) {
                            edges.add(edge);
                        }


                    }
                }
                j++;
            }*/

            //edges.add(edge);[
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }


       nodes.add(new GraphNode("5bb6235d716616bd22fd5035", "Ping"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5036", "Pong"));
        nodes.add(new GraphNode("5bb6235d716616bd22fd5037", "Secure Service"));
        edges.add(new Edge("5bb6235d716616bd22fd5035","5bb6235d716616bd22fd5037","require"));
        edges.add(new Edge("5bb6235d716616bd22fd5037","5bb6235d716616bd22fd5036","require"));



/*       for (int i = 0; i <nodes.size(); i++)
           GraphNode a = nodes.get(i);*/


        return ResponseEntity.ok(new AjaxResponse("Instant Graph", nodes, edges));


    }

}
/*
    @RequestMapping("/graphinstance")
    public ResponseEntity<AjaxResponse> getServiceGraphforInstance(ServiceGraphs serviceGraphs) {
        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();
        List<viseco.sc.helper.Edge> edges = new ArrayList<>();
        List<ServiceGraphs> graphjson=serviceGraphsrRepository.findAll();


        try {
            ObjectMapper mapper = new ObjectMapper();



            // get serviceGraph object as a json string
            String json  = mapper.writeValueAsString(graphjson);
            //byte[] jsonData = graphjson.toString().getBytes();
            //List<ServiceGraphs> serviceGraphs1 = mapper.readValue(graphjson, ServiceGraphs.class);

            //serviceGraphRepository.saveAll(json);


            List<Component> component = componentRespository.findAll();

            int j = 0;

            for (ServiceGraphNodes serviceGraphNodes : graphjson.get(1).getServiceGraphNodes()) {


                //ServiceGraphNodes newNode = new ServiceGraphNodes("", component.get(j).getName());

                    //nodes.add(newNode);


                    //viseco.sc.helper.Edge edge = new viseco.sc.helper.Edge(node.getNodeId(), component.get(1).getId(), "http");

                    //edges.add(edge);


                j++;
            }

            //edges.add(edge);
        } catch (JAXBException e) {
            e.printStackTrace();
        }  catch (IOException e) {

            e.printStackTrace();
        }
        return ResponseEntity.ok(new AjaxResponse("test Graph", nodes, edges));


    }

    }*/

