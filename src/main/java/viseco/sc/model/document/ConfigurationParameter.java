//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.02.06 at 11:22:17 AM EET 
//


package viseco.sc.model.document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}ConfigurationParameterName"/&gt;
 *         &lt;element ref="{}ConfigurationParamaterDescription"/&gt;
 *         &lt;element ref="{}ConfigurationParameterType"/&gt;
 *         &lt;element ref="{}ConfigurationParameterDefaultValue"/&gt;
 *         &lt;element ref="{}MutableAfterStartup"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "configurationParameterName",
        "configurationParamaterDescription",
        "configurationParameterType",
        "configurationParameterDefaultValue",
        "mutableAfterStartup"
})
@XmlRootElement(name = "ConfigurationParameter")
public class ConfigurationParameter {

    @XmlElement(name = "ConfigurationParameterName", required = true)
    protected String configurationParameterName;
    @XmlElement(name = "ConfigurationParamaterDescription", required = true)
    protected String configurationParamaterDescription;
    @XmlElement(name = "ConfigurationParameterType", required = true)
    protected String configurationParameterType;
    @XmlElement(name = "ConfigurationParameterDefaultValue", required = true)
    protected String configurationParameterDefaultValue;
    @XmlElement(name = "MutableAfterStartup")
    protected boolean mutableAfterStartup;

    /**
     * Gets the value of the configurationParameterName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getConfigurationParameterName() {
        return configurationParameterName;
    }

    /**
     * Sets the value of the configurationParameterName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setConfigurationParameterName(String value) {
        this.configurationParameterName = value;
    }

    /**
     * Gets the value of the configurationParamaterDescription property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getConfigurationParamaterDescription() {
        return configurationParamaterDescription;
    }

    /**
     * Sets the value of the configurationParamaterDescription property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setConfigurationParamaterDescription(String value) {
        this.configurationParamaterDescription = value;
    }

    /**
     * Gets the value of the configurationParameterType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getConfigurationParameterType() {
        return configurationParameterType;
    }

    /**
     * Sets the value of the configurationParameterType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setConfigurationParameterType(String value) {
        this.configurationParameterType = value;
    }

    /**
     * Gets the value of the configurationParameterDefaultValue property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getConfigurationParameterDefaultValue() {
        return configurationParameterDefaultValue;
    }

    /**
     * Sets the value of the configurationParameterDefaultValue property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setConfigurationParameterDefaultValue(String value) {
        this.configurationParameterDefaultValue = value;
    }

    /**
     * Gets the value of the mutableAfterStartup property.
     *
     */
    public boolean isMutableAfterStartup() {
        return mutableAfterStartup;
    }

    /**
     * Sets the value of the mutableAfterStartup property.
     *
     */
    public void setMutableAfterStartup(boolean value) {
        this.mutableAfterStartup = value;
    }

}
