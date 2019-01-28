package viseco.sc.model.document;

import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.Entity;
import java.util.List;
@Entity
@Document(collection = "NFVGraph")
public class NSDInfo {
    private String id;
    private String name;

    private List<VNFDInfo> vnfd;
    public NSDInfo(String id, String name, List<VNFDInfo> vnfd) {
        this.id = id;
        this.name = name;
        this.vnfd = vnfd;
    }
    public NSDInfo() { }
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

    public List<VNFDInfo> getVnfd() {
        return vnfd;
    }
    public void setVnfd(List<VNFDInfo> vnfd) {
        this.vnfd = vnfd;
    }


}