package viseco.sc.model.document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "descriptiveCNMetadata",
        "configurationParameters",
        "cnMetrics",
        "distributionParameters",
        "exposedChainableEndpoints",
        "requiredChainableEndpoints",
        "behavioralProfile",
        "executionRequirements"
})
@XmlRootElement(name = "Component")
public class Component {

    @XmlElement(name = "DescriptiveCNMetadata", required = true)
    protected viseco.sc.model.document.DescriptiveCNMetadata descriptiveCNMetadata;
    @XmlElement(name = "ConfigurationParameters")
    protected ConfigurationParameters configurationParameters;
    @XmlElement(name = "CNMetrics")
    protected viseco.sc.model.document.CNMetrics cnMetrics;
    @XmlElement(name = "DistributionParameters")
    protected viseco.sc.model.document.DistributionParameters distributionParameters;
    @XmlElement(name = "ExposedChainableEndpoints", required = true)
    protected viseco.sc.model.document.ExposedChainableEndpoints exposedChainableEndpoints;
    @XmlElement(name = "RequiredChainableEndpoints", required = true)
    protected viseco.sc.model.document.RequiredChainableEndpoints requiredChainableEndpoints;
    @XmlElement(name = "BehavioralProfile")
    protected Component.BehavioralProfile behavioralProfile;
    @XmlElement(name = "ExecutionRequirements")
    protected Component.ExecutionRequirements executionRequirements;

    /**
     * Gets the value of the descriptiveCNMetadata property.
     *
     * @return
     *     possible object is
     *
     *
     */
    public DescriptiveCNMetadata getDescriptiveCNMetadata() {
        return descriptiveCNMetadata;
    }



    public void setDescriptiveCNMetadata(viseco.sc.model.document.DescriptiveCNMetadata value) {
        this.descriptiveCNMetadata = value;
    }

    /**
     * Gets the value of the configurationParameters property.
     *
     * @return
     *     possible object is
     *     {@link ConfigurationParameters }
     *
     */
    public ConfigurationParameters getConfigurationParameters() {
        return configurationParameters;
    }

    /**
     * Sets the value of the configurationParameters property.
     *
     * @param value
     *     allowed object is
     *     {@link ConfigurationParameters }
     *
     */
    public void setConfigurationParameters(ConfigurationParameters value) {
        this.configurationParameters = value;
    }

    /**
     * Gets the value of the cnMetrics property.
     *
     * @return
     *     possible object is
     *
     *
     */
    public viseco.sc.model.document.CNMetrics getCNMetrics() {
        return cnMetrics;
    }

    /**
     * Sets the value of the cnMetrics property.
     *
     * @param value
     *     allowed object is
     *
     *
     */
    public void setCNMetrics(viseco.sc.model.document.CNMetrics value) {
        this.cnMetrics = value;
    }

    /**
     * Gets the value of the distributionParameters property.
     *
     * @return
     *     possible object is
     *
     */
    public viseco.sc.model.document.DistributionParameters getDistributionParameters() {
        return distributionParameters;
    }

    /**
     * Sets the value of the distributionParameters property.
     *
     * @param value
     *     allowed object is
     *
     *
     */
    public void setDistributionParameters(viseco.sc.model.document.DistributionParameters value) {
        this.distributionParameters = value;
    }

    /**
     * Gets the value of the exposedChainableEndpoints property.
     *
     * @return
     *     possible object is
     *
     *
     */
    public viseco.sc.model.document.ExposedChainableEndpoints getExposedChainableEndpoints() {
        return exposedChainableEndpoints;
    }

    /**
     * Sets the value of the exposedChainableEndpoints property.
     *
     * @param value
     *     allowed object is
     *
     *
     */
    public void setExposedChainableEndpoints(viseco.sc.model.document.ExposedChainableEndpoints value) {
        this.exposedChainableEndpoints = value;
    }

    /**
     * Gets the value of the requiredChainableEndpoints property.
     *
     * @return
     *     possible object is
     *
     *
     */
    public viseco.sc.model.document.RequiredChainableEndpoints getRequiredChainableEndpoints() {
        return requiredChainableEndpoints;
    }

    /**
     * Sets the value of the requiredChainableEndpoints property.
     *
     * @param value
     *     allowed object is
     *
     *
     */
    public void setRequiredChainableEndpoints(viseco.sc.model.document.RequiredChainableEndpoints value) {
        this.requiredChainableEndpoints = value;
    }

    /**
     * Gets the value of the behavioralProfile property.
     *
     * @return
     *     possible object is
     *     {@link Component.BehavioralProfile }
     *
     */
    public Component.BehavioralProfile getBehavioralProfile() {
        return behavioralProfile;
    }

    /**
     * Sets the value of the behavioralProfile property.
     *
     * @param value
     *     allowed object is
     *     {@link Component.BehavioralProfile }
     *
     */
    public void setBehavioralProfile(Component.BehavioralProfile value) {
        this.behavioralProfile = value;
    }

    /**
     * Gets the value of the executionRequirements property.
     *
     * @return
     *     possible object is
     *     {@link Component.ExecutionRequirements }
     *
     */
    public Component.ExecutionRequirements getExecutionRequirements() {
        return executionRequirements;
    }

    /**
     * Sets the value of the executionRequirements property.
     *
     * @param value
     *     allowed object is
     *     {@link Component.ExecutionRequirements }
     *
     */
    public void setExecutionRequirements(Component.ExecutionRequirements value) {
        this.executionRequirements = value;
    }


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
     *         &lt;element name="Scalability" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;enumeration value="VERTICAL"/&gt;
     *               &lt;enumeration value="HORIZONTAL"/&gt;
     *               &lt;enumeration value="VERTICAL_HORIZONTAL"/&gt;
     *               &lt;enumeration value="CUSTOM"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ScalabilityHandler" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
            "scalability",
            "scalabilityHandler"
    })
    public static class BehavioralProfile {

        @XmlElement(name = "Scalability")
        protected String scalability;
        @XmlElement(name = "ScalabilityHandler")
        protected String scalabilityHandler;

        /**
         * Gets the value of the scalability property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getScalability() {
            return scalability;
        }

        /**
         * Sets the value of the scalability property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setScalability(String value) {
            this.scalability = value;
        }

        /**
         * Gets the value of the scalabilityHandler property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getScalabilityHandler() {
            return scalabilityHandler;
        }

        /**
         * Sets the value of the scalabilityHandler property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setScalabilityHandler(String value) {
            this.scalabilityHandler = value;
        }

    }


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
     *         &lt;element name="VCPU" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="Memory" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="Storage" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
            "vcpu",
            "memory",
            "storage"
    })
    public static class ExecutionRequirements {

        @XmlElement(name = "VCPU", defaultValue = "1")
        protected int vcpu;
        @XmlElement(name = "Memory", defaultValue = "128")
        protected int memory;
        @XmlElement(name = "Storage")
        protected int storage;

        /**
         * Gets the value of the vcpu property.
         *
         */
        public int getVCPU() {
            return vcpu;
        }

        /**
         * Sets the value of the vcpu property.
         *
         */
        public void setVCPU(int value) {
            this.vcpu = value;
        }

        /**
         * Gets the value of the memory property.
         *
         */
        public int getMemory() {
            return memory;
        }

        /**
         * Sets the value of the memory property.
         *
         */
        public void setMemory(int value) {
            this.memory = value;
        }

        /**
         * Gets the value of the storage property.
         *
         */
        public int getStorage() {
            return storage;
        }

        /**
         * Sets the value of the storage property.
         *
         */
        public void setStorage(int value) {
            this.storage = value;
        }

    }

}

