package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<PageEntity, Long>, JpaSpecificationExecutor<PageEntity> {
}
