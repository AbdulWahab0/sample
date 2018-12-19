package viseco.sc.model.document;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Abdul wahab
 *
 */
@Entity
@Document(collection = "Components")
public class VNFDInfo {
    private String id;
    private String name;
    private String vendor;
    private String version;
    private Date createdDate;
    private String lifecycle_event; //list
    //String configurations;
    public VNFDInfo(String id, String name, String vendor, String version, Date createdDate, String lifecycle_event) {
        this.id = id;
        this.name = name;
        this.vendor = vendor;
        this.version = version;
        this.createdDate=createdDate;
        this.lifecycle_event = lifecycle_event;
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
    public String getLifecycle_event() {
        return lifecycle_event;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public void setLifecycle_event(String lifecycle_event) {
        this.lifecycle_event = lifecycle_event;
    }
}
