package viseco.sc.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import viseco.sc.model.document.VNFDGraph;

public interface VnfdGraphRepository extends MongoRepository<VNFDGraph,String> {
}
