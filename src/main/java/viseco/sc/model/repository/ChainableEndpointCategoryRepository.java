package viseco.sc.model.repository;

import org.springframework.data.repository.CrudRepository;
import viseco.sc.model.domain.ChainableEndpointCategory;

public interface ChainableEndpointCategoryRepository extends CrudRepository<ChainableEndpointCategory, String> {

    ChainableEndpointCategory findByCepcid(String cepcid);

}
