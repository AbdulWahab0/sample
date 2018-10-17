package viseco.sc.model.document;

import javax.xml.bind.annotation.*;
import java.util.List;
import  java.util.ArrayList;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "graphNode"
})
@XmlRootElement(name = "GraphNodeDescriptor")

public class GroundNodeDescriptor {
    @XmlElement(name = "GraphNode", required = true)
    protected List<GraphNode> graphNode;

    /**
     * Gets the value of the graphNode property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the graphNode property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGraphNode().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GraphNode }
     *
     *
     */
    public List<GraphNode> getGraphNode() {
        if (graphNode == null) {
            graphNode = new ArrayList<GraphNode>();
        }
        return this.graphNode;
    }
}
