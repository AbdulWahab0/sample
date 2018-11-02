package viseco.sc.model.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.util.List;
@Entity
@Document(collection = "ServiceGraph")
public class NSDInfo {
    private String id;
    private String name;
    private String vendor;
    private String updateAt;
    private String version;
    private String vnf_dependency; //list
    private List<VNFDInfo> vnfd;

    public NSDInfo(String id, String name, String vendor, String updateAt, String version, String vnf_dependency, List<VNFDInfo> vnfd) {
        this.id = id;
        this.name = name;
        this.vendor = vendor;
        this.updateAt = updateAt;
        this.version = version;
        this.vnf_dependency = vnf_dependency;
        this.vnfd = vnfd;
    }

    public NSDInfo() {
    }

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
    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public String getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getVnf_dependency() {
        return vnf_dependency;
    }
    public void setVnf_dependency(String vnf_dependency) {
        this.vnf_dependency = vnf_dependency;
    }
    public List<VNFDInfo> getVnfd() {
        return vnfd;
    }
    public void setVnfd(List<VNFDInfo> vnfd) {
        this.vnfd = vnfd;
    }


}