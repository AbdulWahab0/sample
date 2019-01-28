package viseco.sc.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import viseco.sc.model.document.NSDInfo;



public interface NSDinfoRepository extends CrudRepository<NSDInfo, String> {
}
