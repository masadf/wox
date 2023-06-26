package md.koloritmarketplace.repository.specification;

import md.koloritmarketplace.model.entity.account.AccountEntity;
import md.koloritmarketplace.model.entity.account.AccountRoleEntity;
import md.koloritmarketplace.model.enums.AccountRole;
import md.koloritmarketplace.model.enums.BuckeStatusEnum;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;

@Service
public final class AccountSpec {
    public static Specification<AccountEntity> fetchSome() {
        return (root, query, builder) -> {
            // в случае с пагинацией, делается второй запрос count(*) и он при если есть fetch ломаеться
            // c ошибкой query specified join fetching, but the owner of the fetched association was not present in the select list
            // смотри решение в комментарии метода
            // в случае если это count то query.getResultType() это long и тогда мы не делаем fetch
            if (query.getResultType() != Long.class) {

            }

            return null;
        };
    }
    public static Specification<AccountEntity> likeFio(String  expression) {

        return (root, query, builder) -> {
            return builder.like(builder.upper(root.get("fio")), "%" + expression+ "%");
        };
    }
    public static Specification<AccountEntity> accountRoleEqual(AccountRole expression) {
        return (root, query, builder) -> {
            return builder.equal(root.join("roles").get("accountRoleName"), expression);
        };
    }

    public static Specification<AccountEntity> accountIdEqual(Long expression) {
        return (root, query, builder) -> {

            return builder.equal(root.get("accountId"), expression);
        };
    }
    public static Specification<AccountEntity> bucketStatusEqual(BuckeStatusEnum expression) {
        return (root, query, builder) -> {
            return builder.equal(root.get("bucketStatus"), expression);
        };
    }

    public static Specification<AccountEntity> eventTypeLanguageCodeEqual(String expression) {

        return (root, query, builder) -> {
            Join<AccountEntity, AccountRoleEntity> eve = root.join("contentList");
            return builder.equal(eve.join("languageEntity").get("languageCode"), expression);
        };
    }
}
