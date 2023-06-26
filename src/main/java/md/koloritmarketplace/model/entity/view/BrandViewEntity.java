package md.koloritmarketplace.model.entity.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import md.koloritmarketplace.model.entity.ImageEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brandview", schema = "marketplace")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BrandViewEntity {

    @Id
    @Column(name = "brandid")
    private Long brandId;

    @Column(name = "brandname")
    private String brandName;

    @Column(name = "counts")
    private Long counts;
}
