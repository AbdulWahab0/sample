package viseco.sc.model.document;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "rpid",
        "rpName"
})
@XmlRootElement(name = "RuntimePolicy")

public class RuntimePolicy {
    @XmlElement(name = "RPID", required = true)
    protected String rpid;
    @XmlElement(name = "RPName", required = true)
    protected String rpName;
    public String getRPID() {
        return rpid;
    }
    public void setRPID(String value) {
        this.rpid = value;
    }
    public String getRPName() {
        return rpName;
    }
    public void setRPName(String value) {
        this.rpName = value;
    }

}
