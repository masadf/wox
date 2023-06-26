package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<AppEntity, Long> {
}
