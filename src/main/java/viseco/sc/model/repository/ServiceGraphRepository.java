package viseco.sc.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import viseco.sc.model.document.GraphInfo;

public interface ServiceGraphRepository extends MongoRepository<GraphInfo,String> {
}
