package md.koloritmarketplace.model.entity;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.entity.option.OptionEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "linkoptionitem", schema = "marketplace")
public class LinkOptionItemEntity {
    @Id
    @Column(name = "linkoptionitemid")
    @SequenceGenerator(name = "linkOptionItemGenerator", sequenceName = "marketplace.linkoptionitem_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "linkOptionItemGenerator")
    private Long linkOptionItemId;

    @ManyToOne
    @JoinColumn(name = "optionid", referencedColumnName = "optionid")
    OptionEntity option;

    @Column(name = "itemid")
    private Long itemId;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "ruvalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "envalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rovalue"))
    })
    private LanguageEmb languageEmb;
}
