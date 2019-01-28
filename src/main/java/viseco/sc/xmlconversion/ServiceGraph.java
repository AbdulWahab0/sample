package viseco.sc.xmlconversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ServiceGraph")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceGraph {
    @XmlElement(name="DescriptiveSGMetadata")
    protected DescriptiveSGMetadata descriptiveSGMetadata;
    @XmlElement(name="GraphNodeDescriptor")
    private GraphNodeDescriptor graphNodeDescriptor;

    public GraphNodeDescriptor getGraphNodeDescriptor() {
        return graphNodeDescriptor;
    }

    public void setGraphNodeDescriptor(GraphNodeDescriptor graphNodeDescriptor) {
        this.graphNodeDescriptor = graphNodeDescriptor;
    }

    public DescriptiveSGMetadata getDescriptiveSGMetadata() {
        return descriptiveSGMetadata;
    }

    public void setDescriptiveSGMetadata(DescriptiveSGMetadata descriptiveSGMetadata) {
        this.descriptiveSGMetadata = descriptiveSGMetadata;
    }
}
