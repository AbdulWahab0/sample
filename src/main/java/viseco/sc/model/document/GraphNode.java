package viseco.sc.model.document;
import java.util.List;
import  java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class GraphNode {
    @XmlElement(name = "NID", required = true)
    public String nid;
    @XmlElement(name = "CNID", required = true)
    public  String cnid;
    @XmlElement(name = "GraphDependency")
    public  List<GraphDependency> graphDependency;

    /**
     * Gets the value of the nid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNID() {
        return nid;
    }

    /**
     * Sets the value of the nid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNID(String value) {
        this.nid = value;
    }

    /**
     * Gets the value of the cnid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCNID() {
        return cnid;
    }

    /**
     * Sets the value of the cnid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCNID(String value) {
        this.cnid = value;
    }

    /**
     * Gets the value of the graphDependency property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the graphDependency property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGraphDependency().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GraphDependency }
     *
     *
     */
    public List<GraphDependency> getGraphDependency() {
        if (graphDependency == null) {
            graphDependency = new ArrayList<GraphDependency>();
        }
        return this.graphDependency;
    }
}
