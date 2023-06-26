package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.account.AccountRoleEntity;
import md.koloritmarketplace.model.enums.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRoleEntity, Long> {
}
