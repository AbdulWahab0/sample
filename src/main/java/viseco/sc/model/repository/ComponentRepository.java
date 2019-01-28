package viseco.sc.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import viseco.sc.model.domain.Component;

import java.util.List;

public interface ComponentRepository extends CrudRepository<Component, String> {

    Component findByCnid(String cnid);

    @Override
    List<Component> findAll();

    List<Component> findByUserName(String userName);

    Page<Component> findByUserName(String userName, Pageable pageable);

    List<Component> findByIsPublic(Boolean isPublic);

    List<Component> findByIsPublicOrUserName(Boolean isPublic,String userName);
}