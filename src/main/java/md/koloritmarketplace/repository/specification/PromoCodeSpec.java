package md.koloritmarketplace.repository.specification;

import md.koloritmarketplace.model.entity.CardEntity;
import md.koloritmarketplace.model.entity.PromoCodeEntity;
import md.koloritmarketplace.model.entity.account.AccountEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public final class PromoCodeSpec {
    public static Specification<PromoCodeEntity> fetchSome() {
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

    public static Specification<PromoCodeEntity> likePromoCode(String  expression) {

        return (root, query, builder) -> {
            return builder.like(builder.upper(root.get("promoCode")), "%" + expression+ "%");
        };
    }
    public static Specification<PromoCodeEntity> percentEqual(Long expression) {
        return (root, query, builder) -> {

            return builder.equal(root.get("percent"), expression);
        };
    }
    public static Specification<PromoCodeEntity> greaterThanStartDate(LocalDate expression) {
        return (root, query, builder) -> {

            return builder.greaterThanOrEqualTo(root.get("startDate"), expression);
        };
    }
    public static Specification<PromoCodeEntity> lessThanEndDate(LocalDate expression) {
        return (root, query, builder) -> {

            return builder.lessThanOrEqualTo(root.get("endDate"), expression);
        };
    }



}
