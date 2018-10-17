package viseco.sc.model.document;

import javax.xml.bind.annotation.*;
import java.util.List;
import  java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "runtimePolicy"
})
@XmlRootElement(name = "RuntimePolicyDescriptor")
public class RuntimePolicyDescriptor {

    @XmlElement(name = "RuntimePolicy", required = true)
    protected List<RuntimePolicy> runtimePolicy;

    /**
     * Gets the value of the runtimePolicy property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the runtimePolicy property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRuntimePolicy().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RuntimePolicy }
     *
     *
     */
    public List<RuntimePolicy> getRuntimePolicy() {
        if (runtimePolicy == null) {
            runtimePolicy = new ArrayList<RuntimePolicy>();
        }
        return this.runtimePolicy;
    }

}

