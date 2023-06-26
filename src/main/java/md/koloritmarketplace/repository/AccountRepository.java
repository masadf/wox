package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>, JpaSpecificationExecutor<AccountEntity> {
    Optional<AccountEntity> findByUserNameAndPassword(String emailAddress, String password);

    AccountEntity findByUserName(String userName);
}
