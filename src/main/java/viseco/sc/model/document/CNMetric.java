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
 *         &lt;element ref="{}MetricName"/&gt;
 *         &lt;element ref="{}MetricDescription"/&gt;
 *         &lt;element ref="{}UnitOfMeasurement"/&gt;
 *         &lt;element ref="{}MetricValueType"/&gt;
 *         &lt;element ref="{}MinValue" minOccurs="0"/&gt;
 *         &lt;element ref="{}MaxValue" minOccurs="0"/&gt;
 *         &lt;element ref="{}HigherIsBetter"/&gt;
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
        "metricName",
        "metricDescription",
        "unitOfMeasurement",
        "metricValueType",
        "minValue",
        "maxValue",
        "higherIsBetter"
})
@XmlRootElement(name = "CNMetric")
public class CNMetric {

    @XmlElement(name = "MetricName", required = true)
    protected String metricName;
    @XmlElement(name = "MetricDescription", required = true)
    protected String metricDescription;
    @XmlElement(name = "UnitOfMeasurement", required = true)
    protected String unitOfMeasurement;
    @XmlElement(name = "MetricValueType", required = true)
    protected String metricValueType;
    @XmlElement(name = "MinValue")
    protected String minValue;
    @XmlElement(name = "MaxValue")
    protected String maxValue;
    @XmlElement(name = "HigherIsBetter")
    protected boolean higherIsBetter;

    /**
     * Gets the value of the metricName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMetricName() {
        return metricName;
    }

    /**
     * Sets the value of the metricName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMetricName(String value) {
        this.metricName = value;
    }

    /**
     * Gets the value of the metricDescription property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMetricDescription() {
        return metricDescription;
    }

    /**
     * Sets the value of the metricDescription property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMetricDescription(String value) {
        this.metricDescription = value;
    }

    /**
     * Gets the value of the unitOfMeasurement property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    /**
     * Sets the value of the unitOfMeasurement property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUnitOfMeasurement(String value) {
        this.unitOfMeasurement = value;
    }

    /**
     * Gets the value of the metricValueType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMetricValueType() {
        return metricValueType;
    }

    /**
     * Sets the value of the metricValueType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMetricValueType(String value) {
        this.metricValueType = value;
    }

    /**
     * Gets the value of the minValue property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMinValue() {
        return minValue;
    }

    /**
     * Sets the value of the minValue property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMinValue(String value) {
        this.minValue = value;
    }

    /**
     * Gets the value of the maxValue property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the value of the maxValue property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMaxValue(String value) {
        this.maxValue = value;
    }

    /**
     * Gets the value of the higherIsBetter property.
     *
     */
    public boolean isHigherIsBetter() {
        return higherIsBetter;
    }

    /**
     * Sets the value of the higherIsBetter property.
     *
     */
    public void setHigherIsBetter(boolean value) {
        this.higherIsBetter = value;
    }

}

