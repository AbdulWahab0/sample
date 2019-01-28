package viseco.sc.xmlconversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="GraphNodeDescriptor")
@XmlAccessorType(XmlAccessType.FIELD)
public class GraphNodeDescriptor {

    @XmlElement(name="GraphNode")
    private List<GraphNode> graphNodes;

    public List<GraphNode> getGraphNodes() {
        return graphNodes;
    }

    public void setGraphNodes(List<GraphNode> graphNodes) {
        this.graphNodes = graphNodes;
    }
}
