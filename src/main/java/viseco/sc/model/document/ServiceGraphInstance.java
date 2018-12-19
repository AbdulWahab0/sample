package viseco.sc.model.document;

import org.springframework.data.mongodb.core.mapping.Document;
import viseco.sc.xmlconversion.GraphDependency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Document(collection = "Sginstance")
public class ServiceGraphInstance {
    @Id
    @GeneratedValue
    @NotNull
    private String id;
    @NotNull
    private String sgId;
    @NotNull
    private String sgName;
    private List<ComponentMetaData> componentMetaData;
    public ServiceGraphInstance() {
    }
    public ServiceGraphInstance(@NotNull String id, @NotNull String sgId, @NotNull String sgName, List<ComponentMetaData> componentMetaData) {
        this.id = id;
        this.sgId = sgId;
        this.sgName = sgName;
        this.componentMetaData = componentMetaData;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSgId() {
        return sgId;
    }
    public void setSgId(String sgId) {
        this.sgId = sgId;
    }
    public String getSgName() {
        return sgName;
    }
    public void setSgName(String sgName) {
        this.sgName = sgName;
    }
    public List<ComponentMetaData> getComponentMetaData() {
        return componentMetaData;
    }
    public void setComponentMetaData(List<ComponentMetaData> componentMetaData) {
        this.componentMetaData = componentMetaData;
    }
}

