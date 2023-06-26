package md.koloritmarketplace.model.entity;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;

import javax.persistence.*;

@Data
@Entity
@Table(name = "country", schema = "marketplace")

public class CountryEntity {
    @Id
    @Column(name = "countryid")
    @SequenceGenerator(name = "countryGenerator", sequenceName = "marketplace.country_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countryGenerator")
    private Long countryId;

    @Column(name = "image")
    private String image;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "ruvalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "envalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rovalue"))
    })
    private LanguageEmb languageEmb;
}
