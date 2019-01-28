package viseco.sc.model.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Document(collection = "AstridNewGraph")
public class AstridGraph {
    private String id;


    private List<GraphNodes> graphNodes;

    public AstridGraph(String id, List<GraphNodes> graphNodes) {
        this.id = id;

        this.graphNodes = graphNodes;
    }
    public AstridGraph() { }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }





    public List<GraphNodes> getGraphNodes() {
        return graphNodes;
    }

    public void setGraphNodes(List<GraphNodes> graphNodes) {
        this.graphNodes = graphNodes;
    }
}
