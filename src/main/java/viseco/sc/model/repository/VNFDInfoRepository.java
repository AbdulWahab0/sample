package viseco.sc.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import viseco.sc.model.document.VNFDInfo;

public interface VNFDInfoRepository extends MongoRepository<VNFDInfo,String> {
}
