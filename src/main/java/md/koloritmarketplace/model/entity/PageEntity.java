package md.koloritmarketplace.model.entity;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.enums.TypePageEnum;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pages", schema = "marketplace")
@PrimaryKeyJoinColumn(name = "pageid")
public class PageEntity extends AppEntity {
    @Column(name = "pageid", insertable = false, updatable = false)
    private Long pageId;

    @Column(name = "pageyear")
    private Long year;

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

    @Column(name = "typepage")
    @Enumerated(EnumType.STRING)
    private TypePageEnum pageType;

}
