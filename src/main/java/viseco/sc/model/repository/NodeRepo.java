package viseco.sc.model.repository;

import org.springframework.data.repository.CrudRepository;
import viseco.sc.model.document.Astridjson;
import viseco.sc.model.document.Node;

public interface NodeRepo extends CrudRepository<Node, String> {
}
