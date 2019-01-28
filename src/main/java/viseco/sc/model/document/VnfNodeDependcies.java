package viseco.sc.model.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Entity
@Document(collection = "AstridNewGraph")
public class VnfNodeDependcies {

    private  String sourceid;
    private  String destinationid;

    public VnfNodeDependcies(String sourceid, String destinationid) {
        this.sourceid = sourceid;
        this.destinationid = destinationid;
    }

    public VnfNodeDependcies() {
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getDestinationid() {
        return destinationid;
    }

    public void setDestinationid(String destinationid) {
        this.destinationid = destinationid;
    }
}
