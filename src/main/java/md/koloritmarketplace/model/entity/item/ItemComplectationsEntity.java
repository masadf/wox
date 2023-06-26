package md.koloritmarketplace.model.entity.item;

import lombok.*;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;

import javax.persistence.*;

@Entity
@Table(name = "itemcomplectations", schema = "marketplace")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemComplectationsEntity {
    @Id
    @Column(name = "itemcomplectationsid", nullable = false)
    @SequenceGenerator(name = "itemComplectations", sequenceName = "marketplace.itemcomplectations_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemComplectations")
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

}
