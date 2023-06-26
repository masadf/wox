package md.koloritmarketplace.model.entity.item;

import lombok.*;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;

import javax.persistence.*;

@Entity
@Table(name = "itemdopinfo", schema = "marketplace")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDopInfoEntity {
    @Id
    @Column(name = "itemdopinfoid", nullable = false)
    @SequenceGenerator(name = "itemDopInfoGenerator", sequenceName = "marketplace.itemdopinfo_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemDopInfoGenerator")
    private Long itemDopInfoId;

    @Column(name = "itemid")
    private Long itemId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "ruvalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "envalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rovalue"))
    })
    private LanguageEmb languageValue;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "runame")),
            @AttributeOverride(name = "enValue", column = @Column(name = "enname")),
            @AttributeOverride(name = "roValue", column = @Column(name = "roname"))
    })
    private LanguageEmb languageName;
}
