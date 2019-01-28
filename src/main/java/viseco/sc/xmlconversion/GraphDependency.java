package viseco.sc.xmlconversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="GraphDependency")
@XmlAccessorType(XmlAccessType.FIELD)
public class GraphDependency {

    @XmlElement(name="SourceID")
    private String sourceid;

    @XmlElement(name="DestinationID")
    private  String destinationid;


    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getDestinationid() {
        return destinationid;
    }

    public void setDestinationid(String destinationid) {
        this.destinationid = destinationid;
    }
}

