package viseco.sc.model.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import javax.validation.constraints.NotNull;

@Entity
@Document(collection= "ServiceGraphs")
public class ServiceGraphs {


    @Id
    @GeneratedValue
    @NotNull
    public String id;
    public  String app_id;
    public String vnf_id;
    private List<ServiceGraphNodes> serviceGraphNodes;


    public ServiceGraphs() {
    }

    public ServiceGraphs(@NotNull String id, String app_id, String vnf_id, List<ServiceGraphNodes> serviceGraphNodes) {
        this.id = id;
        this.app_id = app_id;
        this.vnf_id = vnf_id;
        this.serviceGraphNodes = serviceGraphNodes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getVnf_id() {
        return vnf_id;
    }

    public void setVnf_id(String vnf_id) {
        this.vnf_id = vnf_id;
    }

    public List<ServiceGraphNodes> getServiceGraphNodes() {
        return serviceGraphNodes;
    }

    public void setServiceGraphNodes(List<ServiceGraphNodes> serviceGraphNodes) {
        this.serviceGraphNodes = serviceGraphNodes;
    }
}
