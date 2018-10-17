package viseco.sc.model.document;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "Components")
public class Component {
    @Id
    @GeneratedValue
    @NotNull
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String catageory;
    @NotNull
    private boolean status;
    @NotNull
    private  String description;
    @NotNull
    private Date createdDate;

    public Component() {
    }

    public Component(String id, String name, String catageory, boolean status, String description, Date createdDate) {
        this.id = id;
        this.name = name;
        this.catageory = catageory;
        this.status = status;
        this.description = description;
        this.createdDate = createdDate;
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

    public String getCatageory() {
        return catageory;
    }

    public void setCatageory(String catageory) {
        this.catageory = catageory;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
