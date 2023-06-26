package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.account.AccountEntity;
import md.koloritmarketplace.model.entity.item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>, JpaSpecificationExecutor<ItemEntity> {
    List<ItemEntity> findByIdIn(List<Long> ids);
}
