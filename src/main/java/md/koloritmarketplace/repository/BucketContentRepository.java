package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.bucket.BucketContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketContentRepository extends JpaRepository<BucketContentEntity, Long> {
}
