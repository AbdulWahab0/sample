package viseco.sc.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ComponentConfiguration {

    @Id
    private String id;

    @DBRef
    private GroundedServicegraph serviceGraph;

    private String componentId;

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    private String context;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GroundedServicegraph getServiceGraph() {
        return serviceGraph;
    }

    public void setServiceGraph(GroundedServicegraph serviceGraph) {
        this.serviceGraph = serviceGraph;
    }

    public String getContext() {

        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
