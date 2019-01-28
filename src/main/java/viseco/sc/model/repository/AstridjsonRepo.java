package viseco.sc.model.repository;

import org.springframework.data.repository.CrudRepository;
import viseco.sc.model.document.Astridjson;

public interface AstridjsonRepo extends CrudRepository<Astridjson, String> {
}
