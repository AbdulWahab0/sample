package viseco.sc.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import viseco.sc.model.document.Application;

public interface ApplicationRespository extends MongoRepository<Application,String> {


    //void save(Application application);
}
