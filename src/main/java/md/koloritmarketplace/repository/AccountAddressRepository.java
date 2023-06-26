package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.account.AccountAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountAddressRepository extends JpaRepository<AccountAddressEntity, Long> {
}
