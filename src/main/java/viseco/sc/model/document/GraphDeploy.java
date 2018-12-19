package viseco.sc.model.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Document(collection = "SgVNF")
public class GraphDeploy {
    @Id
    @GeneratedValue
    @NotNull
    private  String id;
    private  String source;
    private String destination;
    private  String port;
    private String protocol;
    public GraphDeploy(String id, String source, String destination, String port, String protocol, String communication) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.port = port;
        this.protocol = protocol;
        this.communication = communication;
    }
    public GraphDeploy() { }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public String getProtocol() {
        return protocol;
    }
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    public String getCommunication() {
        return communication;
    }
    public void setCommunication(String communication) {
        this.communication = communication;
    }
    private String communication;
}
