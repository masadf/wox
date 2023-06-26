package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.bucket.BucketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BucketRepository extends JpaRepository<BucketEntity, Long> , JpaSpecificationExecutor<BucketEntity> {
    Set<BucketEntity> findByBuyerId(Long id);
}
