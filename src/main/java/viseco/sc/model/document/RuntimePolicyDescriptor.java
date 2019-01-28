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
    public List<RuntimePolicy> getRuntimePolicy() {
        if (runtimePolicy == null) {
            runtimePolicy = new ArrayList<RuntimePolicy>();
        }
        return this.runtimePolicy;
    }

}

