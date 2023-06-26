package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.TypeMeasureContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMeasureContentRepository extends JpaRepository<TypeMeasureContentEntity, Long> {
}
