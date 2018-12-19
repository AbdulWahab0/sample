package viseco.sc.model.domain;

import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;


@Document
public class ChainableEndpointCategory {

    @Id
    private String id;

    @Indexed(unique = true)
    private String cepcid;

    private String name;


    private List<String> parameters = new ArrayList<>();



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCepcid() {
        return cepcid;
    }

    public void setCepcid(String cepcid) {
        this.cepcid = cepcid;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParametersAsString() {
        return parameters.stream().collect(Collectors.joining(","));
    }
}

