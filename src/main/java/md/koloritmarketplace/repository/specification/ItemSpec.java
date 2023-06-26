package md.koloritmarketplace.repository.specification;

import md.koloritmarketplace.model.entity.item.ItemEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class ItemSpec {
    public static Specification<ItemEntity> fetchSome() {
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

    public static Specification<ItemEntity> likeArticul(String expression) {

        return (root, query, builder) -> {
            return builder.like(builder.upper(root.get("articul")), "%" + expression + "%");
        };
    }

    public static Specification<ItemEntity> likeModel(String expression) {
        return (root, query, builder) -> {
            return builder.like(builder.upper(root.get("model")), "%" + expression + "%");
        };
    }

    public static Specification<ItemEntity> likeName(String expression) {
        return (root, query, builder) -> {
            return builder.like(builder.upper(root.get("name")), "%" + expression + "%");
        };
    }

    public static Specification<ItemEntity> categoryIdEqual(Long expression) {
        return (root, query, builder) -> {

            return builder.equal(root.get("categoryId"), expression);
        };
    }

    public static Specification<ItemEntity> brandIdEqual(Long expression) {
        return (root, query, builder) -> {

            return builder.equal(root.join("brandEntity").get("brandId"), expression);
        };
    }

    public static Specification<ItemEntity> greaterThanMinPrice(Double expression) {
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get("amount"), expression);
        };
    }

    public static Specification<ItemEntity> lessThanMaxPrice(Double expression) {
        return (root, query, builder) -> {

            return builder.lessThanOrEqualTo(root.get("amount"), expression);
        };
    }

    public static Specification<ItemEntity> optionIn(List<Long> optionsIdList) {
        return (root, query, builder) -> {
            query.distinct(true);
            return builder.in(root.join("options").get("option").get("optionId")).value(optionsIdList);
        };
    }

    public static Specification<ItemEntity> optionLinkInRu(List<String> optionsIdList) {
        return (root, query, builder) -> {
            return builder.in(root.join("options").get("languageEmb").get("ruValue")).value(optionsIdList);
        };
    }

    public static Specification<Object> distinct() {
        return (root, query, cb) -> {
            query.distinct(true);
            return null;
        };
    }
}
