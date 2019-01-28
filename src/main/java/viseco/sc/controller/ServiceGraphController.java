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
import viseco.sc.helper.GraphNode;
import viseco.sc.model.document.*;
import viseco.sc.model.domain.Component;
import viseco.sc.model.repository.*;
import viseco.sc.xmlconversion.GraphDependency;
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
import java.util.Optional;

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
    GraphinfoRepository graphinfoRepository;
    @Autowired
    AstridGraphRepository astridGraphRepository;
    @Autowired AstridjsonRepo astridjsonRepo;
    @Autowired NodeRepo nodeRepo;


    List<GraphDeploy> graphDeploys = new ArrayList<GraphDeploy>();


    @RequestMapping("/graph")
    public String Graph(GraphInfo graphInfo) {
        return "graph";
    }
    /*@PostMapping("/savedeploygraph")
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
    }*/
    @GetMapping("/applicationServiceGraph")
    public String Getapplicationservicegraphs(Model model) {




        return "applicationServiceGraph";
    }
    @GetMapping("/networkServiceGraph")
    public String Getnetworkservicegraphs(Model model) {




        return "networkServiceGraph";
    }
    @RequestMapping("/applicationservicegraph")
    public ResponseEntity<AjaxResponse> Getapplicationservicegraph(GraphInfo graphInfo) {
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

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        nodes.add(new viseco.sc.helper.GraphNode("5bb6235d716616bd22fd5035", "ping"));
        nodes.add(new viseco.sc.helper.GraphNode("5bb6235d716616bd22fd5036", "pong"));
        nodes.add(new viseco.sc.helper.GraphNode("5bb6235d716616bd22fd5036", "pong"));
        nodes.add(new viseco.sc.helper.GraphNode("5bb6235d716616bd22fd5036", "pong"));
        nodes.add(new viseco.sc.helper.GraphNode("5bb6235d716616bd22fd5036", "pong"));
        nodes.add(new viseco.sc.helper.GraphNode("5bb6235d716616bd22fd5036", "pong"));
        nodes.add(new viseco.sc.helper.GraphNode("5bb6235d716616bd22fd5036", "pong"));
        nodes.add(new viseco.sc.helper.GraphNode("5bb6235d716616bd22fd5036", "pong"));
        nodes.add(new viseco.sc.helper.GraphNode("5bb6235d716616bd22fd5036", "pong"));
        nodes.add(new viseco.sc.helper.GraphNode("5bb6235d716616bd22fd5036", "pong"));

        //edges.add(new viseco.sc.helper.Edge("5bb6235d716616bd22fd5035","5bb6235d716616bd22fd5036","abdul"));
        return ResponseEntity.ok(new AjaxResponse( nodes, edges));


    }

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
   /*@RequestMapping("/service-graph")
    public ResponseEntity<AjaxResponse> getServiceGraphData(GraphInfo graphInfo) {
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
        return ResponseEntity.ok(new AjaxResponse( nodes, edges));
}*/

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

        return ResponseEntity.ok(new AjaxResponse( nodes, edges));


    }


    @RequestMapping(value = "/newastridgraph")
    public ResponseEntity<AjaxResponse> getNewgraph ( Astridjson astridjson) {

       List<Node>  nL= new ArrayList<Node>();
        List<Edge>  eL= new ArrayList<Edge>();

        Astridjson astridjsond= new Astridjson();
        for(Astridjson ast:astridjsonRepo.findAll()) {
                for (Node node : ast.nodes) {




                Node n = new Node(node.data.getId(),node.data.getName());
                nL.add(n);
                for (Edge edge :ast.getEdges() ) {
                    Edge.Data edgeData=edge.getData();
                        Edge e=new Edge(edgeData.getSource(),edgeData.getTarget());
                        eL.add(e);
                }
         }

        }
        astridjsond.setNodes(nL);
         astridjsond.setEdges(eL);




return ResponseEntity.ok(new AjaxResponse(astridjsond));


    }

    @RequestMapping("/secgw")
    public  String getVNFComponents(VNFDInfo vnfdInfo,Model model) {

        List<VNFDInfo> vnfdInfoList= new ArrayList<>();
        NSDInfo wrapper = new NSDInfo();


        int j = 0;
        List<viseco.sc.model.document.NSDInfo> nsdInfoss = (List<viseco.sc.model.document.NSDInfo>) NsDinfoRepository.findAll();

        for(VNFDInfo vnfdInfo1:nsdInfoss.get(0).getVnfd()) {


            model.addAttribute("Vnfdetaillist",vnfdInfo);
        }

        return "addnewapplication";
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

        return "components";


    }




    @RequestMapping(value = "/component/{id}", method = RequestMethod.GET)
    public ResponseEntity<AjaxResponse> getComponent_vnf (@ModelAttribute  NSDInfo nsdInfo, @PathVariable("id") String id) {

        viseco.sc.model.document.Component comp=null;

        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();
        List<viseco.sc.helper.Edge> edges = new ArrayList<>();
        List<VNFDInfo> vnfdInfos = new ArrayList<>();
        Optional<viseco.sc.model.document.NSDInfo> nsdInfoss =  NsDinfoRepository.findById(id);


        for (VNFDInfo node :nsdInfoss.get().getVnfd())
        {

        //nodes.add(new GraphNode(comp.getDescriptiveCNMetadata().getCNID(), comp.getDescriptiveCNMetadata().getCNName()));

            nodes.add(new GraphNode(node.getId(), node.getName()));
        }


        return ResponseEntity.ok(new AjaxResponse(nodes, edges));


    }

  /*  @RequestMapping(value = "/component/{id}", method = RequestMethod.POST)
    public String saveComponent_vnf_toDB (@ModelAttribute  NSDInfo nsdInfo, @PathVariable("id") String id) {

        List<Node>  nL= new ArrayList<Node>();
        List<Edge>  eL= new ArrayList<Edge>();

        Node obj= new Node();
        Astridjson astridjson=new Astridjson();
        Optional<NSDInfo> nsdInfoss =  NsDinfoRepository.findById(id);


        for (VNFDInfo nodes :nsdInfoss.get().getVnfd())

        {

           *//* Node n=new Node(nodes.getId(),nodes.getName());

            Node.Data data=node.getData();

            Node node1=new Node(nodes.getId(),nodes.getName());

            nL.add(n);
            Data d = new Data ();
            d.getName(nodes.getName());
*//*
            obj.setId(nodes.getId());
            obj.setName(nodes.getName());
           nodeRepo.save(obj);

        }

        return "addnewapplication";


    }
*/
    @RequestMapping(value = "/application/{id}", method = RequestMethod.POST)
    public ResponseEntity<AjaxResponse> saveAracdiaNode_to_db(@ModelAttribute  GraphInfo info, @PathVariable("id") String id) {
        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();
        List<viseco.sc.helper.Edge> edges = new ArrayList<>();

        Optional<GraphInfo> graph = graphinfoRepository.findById(id);
        String xmlData=graph.get().getXml();


        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ServiceGraph.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlData);
            serviceGraph = (ServiceGraph) unmarshaller.unmarshal(reader);

            for (viseco.sc.xmlconversion.GraphNode node : serviceGraph.getGraphNodeDescriptor().getGraphNodes()) {

                nodes.add(new GraphNode(node.getNodeId(),node.getNodeName()));


                //viseco.sc.helper.Edge edge = new viseco.sc.helper.Edge("5bb6235d716616bd22fd5035", "5bb6235d716616bd22fd5036");

                //edges.add(edge);

            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }



        return ResponseEntity.ok(new AjaxResponse( nodes, edges));


    }


    @RequestMapping(value = "/application/{id}", method = RequestMethod.GET)
    public ResponseEntity<AjaxResponse> showtAracdiaNodeList(@ModelAttribute  GraphInfo info, @PathVariable("id") String id) {
        ServiceGraph serviceGraph = null;
        List<viseco.sc.helper.GraphNode> nodes = new ArrayList<>();
        List<viseco.sc.helper.Edge> edges = new ArrayList<>();

        Optional<GraphInfo> graph = graphinfoRepository.findById(id);
        String xmlData = graph.get().getXml();


        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ServiceGraph.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlData);
            serviceGraph = (ServiceGraph) unmarshaller.unmarshal(reader);

            for (viseco.sc.xmlconversion.GraphNode node : serviceGraph.getGraphNodeDescriptor().getGraphNodes()) {

                nodes.add(new GraphNode(node.getNodeId(), node.getNodeName()));


                //viseco.sc.helper.Edge edge = new viseco.sc.helper.Edge("5bb6235d716616bd22fd5035", "5bb6235d716616bd22fd5036");

                //edges.add(edge);

            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }


        return ResponseEntity.ok(new AjaxResponse( nodes, edges));
    }




}


