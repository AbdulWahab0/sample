package viseco.sc.model.repository;

import com.mongodb.Mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import viseco.sc.model.document.Component;
import viseco.sc.model.document.GraphDeploy;

public interface GraphDeployvnfRepository extends MongoRepository<GraphDeploy,String> {
}
