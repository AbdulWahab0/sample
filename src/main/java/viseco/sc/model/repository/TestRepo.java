package viseco.sc.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import viseco.sc.model.document.test;

public interface TestRepo extends MongoRepository<test,String> {
}
