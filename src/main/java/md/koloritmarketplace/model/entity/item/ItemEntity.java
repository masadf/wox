package md.koloritmarketplace.model.entity.item;

import lombok.Data;
import md.koloritmarketplace.model.entity.AppEntity;
import md.koloritmarketplace.model.entity.BrandEntity;
import md.koloritmarketplace.model.entity.LinkOptionItemEntity;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "item", schema = "marketplace")
@PrimaryKeyJoinColumn(name = "itemid")
public class ItemEntity extends AppEntity {

    @Column(name = "itemid", insertable = false, updatable = false)
    private Long itemId;

    @Column(name = "model")
    private String model;

    @Column(name = "itemname")
    private String name;

    @Column(name = "articul")
    private String articul;

    @Column(name = "itemcount")
    private Long count;

    @Column(name = "categoryid")
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "brandid", referencedColumnName = "brandid")
    private BrandEntity brandEntity;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "disamount")
    private Double disAmount;

    @Column(name = "itemcode")
    private String itemCode;


    @OneToMany(mappedBy = "itemId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<LinkOptionItemEntity> options;

    @OneToMany(mappedBy = "itemId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ItemDopInfoEntity> dopsList = new ArrayList<>();

    @OneToMany(mappedBy = "itemId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ItemComplectationsEntity> complectationsList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemdescriptionid", referencedColumnName = "itemdescriptionid")
    private ItemDescriptionEntity descriptions;

    @Transient
    public ObjectTypeEnum getObjectTypeEnum() {
        return ObjectTypeEnum.ITEM;
    }
}
