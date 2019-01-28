package viseco.sc.model.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Document(collection = "Test_Test")
public class test {
    @Id
    @GeneratedValue
    @NotNull
    private  String id;
    private  String name;

    private  String xml;

    public test(@NotNull String id, String name, String xml) {
        this.id = id;
        this.name = name;
        this.xml=xml;



    }

    public test() {
    }

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
