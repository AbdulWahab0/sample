package viseco.sc.model.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@Document(collection = "AstridNewGraph")
public class VNFDGraph {
    private String id;
    private String name;

    private List<VnfNodeDependcies> vnfNodeDependcies;

    public VNFDGraph(String id, String name,  List<VnfNodeDependcies> vnfNodeDependcies) {
        this.id = id;
        this.name = name;

        this.vnfNodeDependcies = vnfNodeDependcies;
    }

    public VNFDGraph() { }
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


    public List<VnfNodeDependcies> getVnfNodeDependcies() {
        return vnfNodeDependcies;
    }

    public void setVnfNodeDependcies(List<VnfNodeDependcies> vnfNodeDependcies) {
        this.vnfNodeDependcies = vnfNodeDependcies;
    }
}
