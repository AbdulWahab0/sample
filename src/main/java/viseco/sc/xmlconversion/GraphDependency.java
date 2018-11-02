package viseco.sc.xmlconversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="GraphDependency")
@XmlAccessorType(XmlAccessType.FIELD)
public class GraphDependency {
/*

    @XmlElement(name="CEPCID")
    private String cepid;

    @XmlElement(name="ECEPID")
    private  String ecepid;
*/

    @XmlElement(name="NodeID")
    private String nodeId;

  /*  public String getCepid() {
        return cepid;
    }

    public void setCepid(String cepid) {
        this.cepid = cepid;
    }

    public String getEcepid() {
        return ecepid;
    }

    public void setEcepid(String ecepid) {
        this.ecepid = ecepid;
    }*/

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}

