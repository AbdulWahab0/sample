package viseco.sc.model.document;

import javax.persistence.Entity;

@Entity
public class ComponentMetaData {


    private  String name;
    private  String status;
    private  int port;
    private  String protocol;


    public ComponentMetaData(String name, String status, int port, String protocol) {
        this.name = name;
        this.status = status;
        this.port = port;
        this.protocol = protocol;
    }

    public ComponentMetaData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
