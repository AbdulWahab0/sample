package viseco.sc.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import viseco.sc.model.document.ServiceGraphInstance;

public interface ServiceGraphInstanceRespository extends MongoRepository<ServiceGraphInstance,String> {
}
