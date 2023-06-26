package md.koloritmarketplace.model.entity.bucket;

import lombok.*;
import md.koloritmarketplace.model.entity.CountryEntity;
import md.koloritmarketplace.model.entity.item.ItemEntity;

import javax.persistence.*;

@Entity
@Table(name = "bucketcontent", schema = "marketplace")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketContentEntity {
    @Id
    @Column(name = "bucketcontentid", nullable = false)
    @SequenceGenerator(name = "bucketContentGenerator", sequenceName = "marketplace.bucketcontent_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bucketContentGenerator")
    private Long bucketContentId;

    @ManyToOne
    @JoinColumn(name = "itemid", referencedColumnName = "itemid")
    private ItemEntity item;

    @Column(name = "bucketid")
    private Long bucketId;

    @Column(name = "countunit")
    private Long countUnit;

    @Column(name = "amountunit")
    private Double amountUnit;

    @Column(name = "amount")
    private Double amount;

}
