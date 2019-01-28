package viseco.sc.model.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Document(collection= "ArcadiaGraph")
public class GraphInfo {
    @Id
    @GeneratedValue
    @NotNull
    public String id;
    public  String name;
    public String xml;

    public GraphInfo(@NotNull String id, String name, String xml) {
        this.id = id;
        this.name = name;
        this.xml = xml;
    }

    public GraphInfo() { }
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
    public String getXml() {
        return xml;
    }
    public void setXml(String xml) {
        this.xml = xml;
    }

}

