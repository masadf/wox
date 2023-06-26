package md.koloritmarketplace.repository.view;

import md.koloritmarketplace.model.entity.view.BrandViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandViewRepository extends JpaRepository<BrandViewEntity, Long>, JpaSpecificationExecutor {
}
