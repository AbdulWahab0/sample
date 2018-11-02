package viseco.sc.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import viseco.sc.model.document.NSDInfo;

import javax.persistence.Id;

public interface NSDinfoRepository extends CrudRepository<NSDInfo, String> {
}
