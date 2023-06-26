package md.koloritmarketplace.model.entity;

import lombok.*;
import md.koloritmarketplace.model.enums.ImageTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "images", schema = "marketplace")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageEntity {
    @Id
    @Column(name = "imageid", nullable = false)
    @SequenceGenerator(name = "imageGenerator", sequenceName = "marketplace.image_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageGenerator")
    private Long imageId;

    @Column(name = "appid")
    private Long appId;

    @Column(name = "value")
    private String value;

    @Column(name = "imagetype")
    @Enumerated(EnumType.STRING)
    private ImageTypeEnum imageType;
}
