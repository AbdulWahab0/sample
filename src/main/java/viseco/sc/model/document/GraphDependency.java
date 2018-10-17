package viseco.sc.model.document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "cepcid",
        "ecepid",
        "nid"
})
public class GraphDependency {
    @XmlElement(name = "CEPCID", required = true)
    protected String cepcid;
    @XmlElement(name = "ECEPID", required = true)
    protected String ecepid;
    @XmlElement(name = "NID", required = true)
    protected String nid;

    /**
     * Gets the value of the cepcid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCEPCID() {
        return cepcid;
    }

    /**
     * Sets the value of the cepcid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCEPCID(String value) {
        this.cepcid = value;
    }

    /**
     * Gets the value of the ecepid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getECEPID() {
        return ecepid;
    }

    /**
     * Sets the value of the ecepid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setECEPID(String value) {
        this.ecepid = value;
    }

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
}
