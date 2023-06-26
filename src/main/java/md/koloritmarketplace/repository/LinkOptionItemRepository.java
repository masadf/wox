package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.LinkOptionItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkOptionItemRepository extends JpaRepository<LinkOptionItemEntity, Long> {
}
