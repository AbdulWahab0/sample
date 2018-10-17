package viseco.sc.model.document;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import viseco.sc.model.repository.ApplicationRespository;

import javax.persistence.Id;


@Document
public class Application {
    @Id
    private Integer id;
    private String name;
    private String status;
    private String author;

    public Application(Integer id, String name, String status, String author) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.author = author;
    }

    public Application() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
