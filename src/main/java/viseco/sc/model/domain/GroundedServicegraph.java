package viseco.sc.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document
public class GroundedServicegraph {

    @Id
    private String id;

    //this is redundant servicegraph is contained in serviceGraphPolicy
 /*   @DBRef
    private Servicegraph servicegraph;

    @DBRef
    private ServiceGraphPolicy serviceGraphPolicy;*/

    private Boolean isMonitored;

   /* private Keypair keypair;*/

    private String username;

    public Map<String, String> componentConfiguration = new HashMap<>();

    public GroundedServicegraph() {

    }

    public GroundedServicegraph( String username) {

        this.username = username;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public Boolean getMonitored() {
        return isMonitored;
    }

    public void setMonitored(Boolean monitored) {
        isMonitored = monitored;
    }

    public Map<String, String> getComponentConfiguration() {
        return componentConfiguration;
    }

    public void setComponentConfiguration(Map<String, String> componentConfiguration) {
        this.componentConfiguration = componentConfiguration;
    }


}
