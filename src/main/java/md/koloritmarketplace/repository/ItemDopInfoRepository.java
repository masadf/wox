package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.item.ItemDopInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDopInfoRepository extends JpaRepository<ItemDopInfoEntity,Long> {
}
