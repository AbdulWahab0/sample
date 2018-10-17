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

    /**
     * Gets the value of the rpid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRPID() {
        return rpid;
    }

    /**
     * Sets the value of the rpid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRPID(String value) {
        this.rpid = value;
    }

    /**
     * Gets the value of the rpName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRPName() {
        return rpName;
    }

    /**
     * Sets the value of the rpName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRPName(String value) {
        this.rpName = value;
    }

}
