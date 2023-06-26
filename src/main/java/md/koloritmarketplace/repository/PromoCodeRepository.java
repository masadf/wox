package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.PromoCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCodeEntity, Long>, JpaSpecificationExecutor<PromoCodeEntity> {

}
