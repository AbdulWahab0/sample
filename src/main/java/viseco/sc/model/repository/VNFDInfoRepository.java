package viseco.sc.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import viseco.sc.model.document.VNFDInfo;
import viseco.sc.model.domain.Component;

import java.util.List;
import java.util.Optional;

public interface VNFDInfoRepository extends CrudRepository<VNFDInfo,String> {






}
