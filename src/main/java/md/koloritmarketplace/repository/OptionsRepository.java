package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.option.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionsRepository extends JpaRepository<OptionEntity, Long>, JpaSpecificationExecutor {
}
