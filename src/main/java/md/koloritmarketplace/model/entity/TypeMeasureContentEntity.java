package md.koloritmarketplace.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "typemeasurecontent", schema = "marketplace")
public class TypeMeasureContentEntity {
    @Id
    @Column(name = "typemeasurecontentid")
    @SequenceGenerator(name = "typeMeasureContentGen", sequenceName = "marketplace.typemeasurecontent_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "typeMeasureContentGen")
    private Long typeMeasureContentId;

    @Column(name = "optionid")
    private Long optionId;

    @Column(name = "value")
    private String value;

}
