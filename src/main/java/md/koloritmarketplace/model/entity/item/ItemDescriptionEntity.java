package md.koloritmarketplace.model.entity.item;

import lombok.*;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;

import javax.persistence.*;

@Entity
@Table(name = "itemdescription", schema = "marketplace")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDescriptionEntity {
    @Id
    @Column(name = "itemdescriptionid", nullable = false)
    @SequenceGenerator(name = "itemDescriptionGenerator", sequenceName = "marketplace.itemdescription_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemDescriptionGenerator")
    private Long itemDescriptionId;

//    @Column(name = "itemid")
//    private Long itemId;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "ruvalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "envalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rovalue"))
    })
    private LanguageEmb languageEmb;
}
