package viseco.sc.helper;

public class Edge {
    String source;
    String target;
    String caption;

    public Edge(String source, String target, String caption) {
        this.source = source;
        this.target = target;
        this.caption = caption;
    }

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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
