package viseco.sc.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;


@Document
public class Component {
    @Id
    private String id;
    @Indexed(unique = true)
    private String cnid;
    private String userName;
    private Boolean isPublic;
    private String xml;

    public Component(String id, String cnid, String userName, Boolean isPublic, String xml) {
        this.id = id;
        this.cnid = cnid;
        this.userName = userName;
        this.isPublic = isPublic;
        this.xml = xml;
    }

    public Component() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnid() {
        return cnid;
    }

    public void setCnid(String cnid) {
        this.cnid = cnid;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

