package viseco.sc.helper;

import org.springframework.validation.ObjectError;
import viseco.sc.model.document.Astridjson;

import java.util.List;

public class AjaxResponse {

    private Object nodes;
    private Object edges;
    private Object astridjson;

    public AjaxResponse(Object nodes, Object edges) {
        this.nodes = nodes;
        this.edges = edges;
    }
    public AjaxResponse(Object astridjson) {
        this.astridjson = astridjson;
    }

    public Object getAstridjson() {
        return astridjson;
    }

    public void setAstridjson(Object astridjson) {
        this.astridjson = astridjson;
    }

    public Object getNodes() {
        return nodes;
    }

    public void setNodes(Object nodes) {
        this.nodes = nodes;
    }

    public Object getEdges() {
        return edges;
    }

    public void setEdges(Object edges) {
        this.edges = edges;
    }
}
