package md.koloritmarketplace.model.entity.option;

import lombok.Data;
import md.koloritmarketplace.model.entity.AppEntity;
import md.koloritmarketplace.model.entity.LinkCountryBrandEntity;
import md.koloritmarketplace.model.entity.TypeMeasureContentEntity;
import md.koloritmarketplace.model.entity.TypeMeasureEntity;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "optionss", schema = "marketplace")
@PrimaryKeyJoinColumn(name = "optionid")
public class OptionEntity extends AppEntity {

    @Column(name = "optionid", insertable = false, updatable = false)
    private Long optionId;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "ruvalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "envalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rovalue"))
    })
    private LanguageEmb optionName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "rucvalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "encvalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rocvalue"))
    })
    private LanguageEmb showcaseOptionName;

    @Column(name = "categoryid")
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "typemeasureid", referencedColumnName = "typemeasureid")
    TypeMeasureEntity typeMeasure;

    @Column(name = "typeoption")
    private String typeOption;
    @OneToMany(mappedBy = "optionId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<TypeMeasureContentEntity> typeMeasureContent= new ArrayList<>();

    @Transient
    public ObjectTypeEnum getObjectTypeEnum() {
        return ObjectTypeEnum.OPTION;
    }
}
