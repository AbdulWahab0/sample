package viseco.sc.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import viseco.sc.model.document.ServiceGraphs;


public interface ServiceGraphsrRepository extends MongoRepository<ServiceGraphs,String> {
}
