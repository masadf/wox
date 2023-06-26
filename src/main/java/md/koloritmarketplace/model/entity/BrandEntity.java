package md.koloritmarketplace.model.entity;

import lombok.Data;
import md.koloritmarketplace.model.entity.bucket.BucketContentEntity;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "brand", schema = "marketplace")
@PrimaryKeyJoinColumn(name = "brandid")
public class BrandEntity  extends AppEntity{
    @Column(name = "brandid", insertable = false, updatable = false)
    private Long brandId;
    @Column(name = "brandname")
    private String brandName;

    @OneToMany(mappedBy = "brandId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    Set<LinkCountryBrandEntity> countries = new HashSet<>();

    @Transient
    public ObjectTypeEnum getObjectTypeEnum() {
        return ObjectTypeEnum.BRAND;
    }
}
