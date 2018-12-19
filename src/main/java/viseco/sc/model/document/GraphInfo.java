package viseco.sc.model.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Document(collection= "SgAPP")
public class GraphInfo {
    @Id
    @GeneratedValue
    @NotNull
    public String id;
    public  String name;
    public String xml;
    public String vendor;
    public  String version;
    public  String serviceGraph;
    private Date createdDate;

    public GraphInfo(@NotNull String id, String name, String xml,String vendor,String version,String serviceGraph,Date createdDate) {
        this.id = id;
        this.name = name;
        this.xml = xml;
        this.vendor=vendor;
        this.version=version;
        this.serviceGraph=serviceGraph;
        this.createdDate=createdDate;
    }
    public String getServiceGraph() {
        return serviceGraph;
    }
    public void setServiceGraph(String serviceGraph) {
        this.serviceGraph = serviceGraph;
    }
    public GraphInfo() { }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getXml() {
        return xml;
    }
    public void setXml(String xml) {
        this.xml = xml;
    }
    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}

