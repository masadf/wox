package md.koloritmarketplace.repository.specification;

import md.koloritmarketplace.model.entity.bucket.BucketContentEntity;
import md.koloritmarketplace.model.entity.bucket.BucketEntity;
import md.koloritmarketplace.model.entity.view.BrandViewEntity;
import md.koloritmarketplace.model.enums.BuckeStatusEnum;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;

@Service
public final class BrandSpec {
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
    public static Specification<BrandViewEntity> likeName(String  expression) {

        return (root, query, builder) -> {
            return builder.like(root.get("brandName"), "%" + expression+ "%");
        };
    }
}
