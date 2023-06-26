package md.koloritmarketplace.model.entity;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;

import javax.persistence.*;

@Data
@Entity
@Table(name = "news", schema = "marketplace")
@PrimaryKeyJoinColumn(name = "newsid")
public class NewsEntity extends AppEntity {
    @Column(name = "newsid", insertable = false, updatable = false)
    private Long newsId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "rutitlevalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "entitlevalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rotitlevalue"))
    })
    private LanguageEmb languageTitleEmb;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "rucontentvalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "encontentvalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rocontentvalue"))
    })
    private LanguageEmb languageContentEmb;


    @Transient
    public ObjectTypeEnum getObjectTypeEnum() {
        return ObjectTypeEnum.NEWS;
    }
}
