package md.koloritmarketplace.repository.specification;

import md.koloritmarketplace.model.entity.CardEntity;
import md.koloritmarketplace.model.entity.account.AccountEntity;
import md.koloritmarketplace.model.entity.account.AccountRoleEntity;
import md.koloritmarketplace.model.enums.AccountRole;
import md.koloritmarketplace.model.enums.BuckeStatusEnum;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;

@Service
public final class CardSpec {
    public static Specification<CardEntity> fetchSome() {
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

    public static Specification<CardEntity> accountIdEqual(Long expression) {
        return (root, query, builder) -> {
            return builder.equal(root.join("owner").get("accountId"), expression);
        };
    }

    public static Specification<CardEntity> percentEqual(Long expression) {
        return (root, query, builder) -> {

            return builder.equal(root.get("percent"), expression);
        };
    }
    public static Specification<CardEntity> cardNumberEqual(String expression) {
        return (root, query, builder) -> {

            return builder.equal(root.get("numberCard"), expression);
        };
    }


}
