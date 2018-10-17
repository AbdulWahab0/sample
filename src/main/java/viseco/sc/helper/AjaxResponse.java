package viseco.sc.helper;

import org.springframework.validation.ObjectError;

import java.util.List;

public class AjaxResponse {
    private String comment;
    private Object nodes;
    private Object edges;

    public AjaxResponse(String comment, Object nodes, Object edges) {
        this.comment = comment;
        this.nodes = nodes;
        this.edges = edges;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
