package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.item.ItemDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDescriptionRepository extends JpaRepository<ItemDescriptionEntity,Long> {
}
