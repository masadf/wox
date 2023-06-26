package md.koloritmarketplace.repository.specification;

import md.koloritmarketplace.model.entity.bucket.BucketContentEntity;
import md.koloritmarketplace.model.entity.bucket.BucketEntity;
import md.koloritmarketplace.model.enums.BuckeStatusEnum;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;

@Service
public final class BucketSpec {
    public static Specification<BucketEntity> fetchSome() {
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

    public static Specification<BucketEntity> bucketLessAmount(Double expression) {
        return (root, query, builder) -> {
            return builder.lessThan(root.get("amount"), expression);
        };
    }

    public static Specification<BucketEntity> bucketGreaterAmount(Double expression) {
        return (root, query, builder) -> {
            return builder.greaterThan(root.get("amount"), expression);
        };
    }
    public static Specification<BucketEntity> bucketIdEqual(Long expression) {
        return (root, query, builder) -> {
            return builder.equal(root.get("bucketId"), expression);
        };
    }
    public static Specification<BucketEntity> accountIdEqual(Long expression) {
        return (root, query, builder) -> {
            return builder.equal(root.get("accountId"), expression);
        };
    }
    public static Specification<BucketEntity> bucketStatusEqual(BuckeStatusEnum expression) {
        return (root, query, builder) -> {
            return builder.equal(root.get("bucketStatus"), expression);
        };
    }
    public static Specification<BucketEntity> eventTypeLanguuageCodeEqual(String expression) {

        return (root, query, builder) -> {
            Join<BucketEntity, BucketContentEntity> eve = root.join("contentList");
            return builder.equal(eve.join("languageEntity").get("languageCode"), expression);
        };
    }
    public static Specification<BucketEntity> likeFio(String  expression) {
        return (root, query, builder) -> {

            return builder.like(builder.upper(root.join("buyer").get("fio")), "%" + expression+ "%");
        };
    }
    public static Specification<BucketEntity> likePhoneNumber(String  expression) {

        return (root, query, builder) -> {
            return builder.like(root.join("buyer").join("contacts").get("phoneNumber"), "%" + expression+ "%");
        };
    }
}
