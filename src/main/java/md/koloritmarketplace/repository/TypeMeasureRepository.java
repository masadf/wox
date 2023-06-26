package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.TypeMeasureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMeasureRepository extends JpaRepository<TypeMeasureEntity, Long> {
}
