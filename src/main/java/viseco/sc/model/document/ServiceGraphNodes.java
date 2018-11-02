package viseco.sc.model.document;

import org.hibernate.validator.constraints.EAN;

import java.util.List;

public class ServiceGraphNodes {
    private String source_ID;
    private String service_ID;
    private String destination_ID;
    private List<GraphDependency> graphDependencies;



    public ServiceGraphNodes(String source_ID, String service_ID, String destination_ID, List<GraphDependency> graphDependencies) {
        this.source_ID = source_ID;
        this.service_ID = service_ID;
        this.destination_ID = destination_ID;
        this.graphDependencies = graphDependencies;
    }

    public ServiceGraphNodes() {
    }

    public String getSource_ID() {
        return source_ID;
    }

    public void setSource_ID(String source_ID) {
        this.source_ID = source_ID;
    }

    public String getService_ID() {
        return service_ID;
    }

    public void setService_ID(String service_ID) {
        this.service_ID = service_ID;
    }

    public String getDestination_ID() {
        return destination_ID;
    }

    public void setDestination_ID(String destination_ID) {
        this.destination_ID = destination_ID;
    }

    public List<GraphDependency> getGraphDependencies() {
        return graphDependencies;
    }

    public void setGraphDependencies(List<GraphDependency> graphDependencies) {
        this.graphDependencies = graphDependencies;
    }
}
