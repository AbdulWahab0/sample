package viseco.sc.model.repository;

import viseco.sc.model.document.Component;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ComponentRespository extends MongoRepository<Component,String> {

    List<Component> findByCatageory(String category_name);

}
