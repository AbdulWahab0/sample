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
    @XmlElement(name="NID")
    private String nid;

    @XmlElement(name="CNID")
    private String cnid;

    @XmlElement(name="GraphDependency")
    private List<GraphDependency> graphDependencies;

    private  List<Component> components;
    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getCnid() {
        return cnid;
    }

    public void setCnid(String cnid) {
        this.cnid = cnid;
    }

    public List<GraphDependency> getGraphDependencies() {
        return graphDependencies;
    }

    public void setGraphDependencies(List<GraphDependency> graphDependencies) {
        this.graphDependencies = graphDependencies;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}