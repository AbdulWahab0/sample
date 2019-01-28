package viseco.sc.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import viseco.sc.model.document.GraphNodes;

public interface GraphNodesRepository extends MongoRepository<GraphNodes,String> {
}
