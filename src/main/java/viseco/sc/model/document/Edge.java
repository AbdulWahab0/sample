package viseco.sc.model.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Entity
@Document(collection = "TestGraph")
public class Edge {


    private final String source;
    private final String target;
    public Data data;
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public Edge(String source, String target) {
        this.source = source;
        this.target = target;
        Data d=new Data();
        d.setSource(this.source);
        d.setTarget(this.target);
        setData(d);
    }


    public class Data {

        String source;
        String target;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }
    }
}
