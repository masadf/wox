package md.koloritmarketplace.repository.specification;

import md.koloritmarketplace.model.entity.account.AccountEntity;
import md.koloritmarketplace.model.entity.account.AccountRoleEntity;
import md.koloritmarketplace.model.entity.option.OptionEntity;
import md.koloritmarketplace.model.enums.AccountRole;
import md.koloritmarketplace.model.enums.BuckeStatusEnum;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import java.util.List;

@Service
public final class OptionSpec {
    public static Specification<OptionEntity> fetchSome() {
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

    public static Specification<OptionEntity> likeOptionName(String  expression) {

        return (root, query, builder) -> {
            return builder.like(root.get("optionName").get("ruValue"), "%" + expression+ "%");
        };
    }
    public static Specification<OptionEntity> likeTypeMeasureValue(String  expression) {

        return (root, query, builder) -> {
            return builder.like(root.join("typeMeasure").get("languageEmb").get("ruValue"), "%" + expression+ "%");
        };
    }
    public static Specification<OptionEntity> categoryIn(List<Long> expression) {

        return (root, query, builder) -> {
            return builder.in(root.get("categoryId")).value(expression);
        };
    }
    public static Specification<OptionEntity> categoryEqual(Long expression) {

        return (root, query, builder) -> {
            return builder.equal(root.get("categoryId"), expression);
        };
    }

}
