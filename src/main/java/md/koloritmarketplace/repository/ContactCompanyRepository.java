package md.koloritmarketplace.repository;

import md.koloritmarketplace.model.entity.ContactCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactCompanyRepository extends JpaRepository<ContactCompanyEntity, Long> {
}
