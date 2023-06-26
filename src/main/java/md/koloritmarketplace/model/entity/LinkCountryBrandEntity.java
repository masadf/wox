package md.koloritmarketplace.model.entity;

import lombok.*;
import md.koloritmarketplace.model.enums.CountryTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "linkcountrybrand", schema = "marketplace")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LinkCountryBrandEntity {
    @Id
    @Column(name = "linkcountrybrandid")
    @SequenceGenerator(name = "linkCountryBrandGenerator", sequenceName = "marketplace.linkcountrybrand_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "linkCountryBrandGenerator")
    private Long linkCountryBrandId;

    @ManyToOne
    @JoinColumn(name = "countryid", referencedColumnName = "countryid")
    private CountryEntity countryId;

    @Column(name = "brandid")
    private Long brandId;

    @Column(name = "linkbrandcountrytype")
    private CountryTypeEnum type;
}
