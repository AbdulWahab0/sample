package viseco.sc.xmlconversion;

import viseco.sc.model.document.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="GraphNode")
@XmlAccessorType(XmlAccessType.FIELD)
public class GraphNode {
    @XmlElement(name="NodeID")
    private String nodeId;
    @XmlElement(name="GraphDependency")
    private List<GraphDependency> graphDependencies;
    public String getNodeId() {
        return nodeId;
    }
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
    public List<GraphDependency> getGraphDependencies() {
        return graphDependencies;
    }
    public void setGraphDependencies(List<GraphDependency> graphDependencies) {
        this.graphDependencies = graphDependencies;
    }

}
