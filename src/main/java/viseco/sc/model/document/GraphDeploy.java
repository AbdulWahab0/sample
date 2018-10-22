package viseco.sc.model.document;

public class GraphDeploy {
    private  String name;
    private String status;
    private  String port;
    private String protocol;
    public GraphDeploy(String name, String status, String port, String protocol) {
        this.name = name;
        this.status = status;
        this.port = port;
        this.protocol = protocol;
    }

    public GraphDeploy() {
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

    @Override
    public String toString() {
        return "GraphDeploy{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", port='" + port + '\'' +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}
