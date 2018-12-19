package viseco.sc.model.repository;

import org.springframework.data.repository.CrudRepository;
import viseco.sc.model.domain.ComponentConfiguration;

public interface ComponentConfigurationRepository extends CrudRepository<ComponentConfiguration, String> {

    ComponentConfiguration findByServiceGraphAndComponentId (String applicationId, String componentId);

    void deleteByServiceGraph(String applicationId);
}
