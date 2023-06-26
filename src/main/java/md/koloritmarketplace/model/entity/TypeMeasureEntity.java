package md.koloritmarketplace.model.entity;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;

import javax.persistence.*;

@Data
@Entity
@Table(name = "typemeasure", schema = "marketplace")
public class TypeMeasureEntity {
    @Id
    @Column(name = "typemeasureid")
    @SequenceGenerator(name = "typeMeasureGenerator", sequenceName = "marketplace.typemeasure_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "typeMeasureGenerator")
    private Long typMeasureId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "ruvalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "envalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rovalue"))
    })
    private LanguageEmb languageEmb;
    @Transient
    public ObjectTypeEnum getObjectTypeEnum() {
        return ObjectTypeEnum.TYPEMEASURE;
    }
}
