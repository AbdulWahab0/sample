package viseco.sc.model.document;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Entity
@Document(collection = "TestGraph")

public class Node {

    private final String id;
    private final String name;
    public Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Node(String id, String name) {
        this.id = id;
        this.name = name;
        Data d = new Data();
        d.setId(this.id);
        d.setName(this.name);
        setData(d);
    }

    public class Data{
        String id;
        String name;

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
    };


}

