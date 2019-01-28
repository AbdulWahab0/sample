package viseco.sc.model.document;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

/**
 * @author Abdul wahab
 *
 */
@Entity
@Document(collection = "NFVGraph")
public class VNFDInfo {
    @Id
    private String id;
    private String name;
    private String vendor;
    private String version;
    private List<VnfDependency> vnf_dependencies;




    public VNFDInfo(String id, String name, List<VnfDependency> vnf_dependencies,String vendor,String version) {
        this.id = id;
        this.name = name;
        this.vendor=vendor;
        this.version=version;
        this.vnf_dependencies = vnf_dependencies;
    }
    public List<VnfDependency> getVnf_dependencies() {
        return vnf_dependencies;
    }

    public void setVnf_dependencies(List<VnfDependency> vnf_dependencies) {
        this.vnf_dependencies = vnf_dependencies;
    }


    public VNFDInfo() { }
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
