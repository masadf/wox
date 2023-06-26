package md.koloritmarketplace.repository.specification;

import md.koloritmarketplace.model.entity.CategoryEntity;
import md.koloritmarketplace.model.entity.account.AccountEntity;
import md.koloritmarketplace.model.entity.bucket.BucketEntity;
import md.koloritmarketplace.model.entity.view.BrandViewEntity;
import md.koloritmarketplace.model.enums.AccountRole;
import md.koloritmarketplace.model.enums.ObjectStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public final class CategorySpec {
    public static Specification<CategoryEntity> fetchSome() {
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

    public static Specification<CategoryEntity> equalStatus(ObjectStatus expression) {
        return (root, query, builder) -> {
            return builder.equal(root.get("status"), expression);
        };
    }
    public static Specification<CategoryEntity> equalParentId(Long expression) {
        return (root, query, builder) -> {
            return builder.equal(root.get("parentId"), expression);
        };
    }
    public static Specification<CategoryEntity> equalParentIdIsNull() {
        return (root, query, builder) -> {
            return builder.isNull(root.get("parentId"));
        };
    }
}
