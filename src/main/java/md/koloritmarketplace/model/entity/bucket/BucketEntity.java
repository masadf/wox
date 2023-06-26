package md.koloritmarketplace.model.entity.bucket;

import lombok.Data;
import md.koloritmarketplace.model.entity.AppEntity;
import md.koloritmarketplace.model.entity.CountryEntity;
import md.koloritmarketplace.model.entity.account.AccountAddressEntity;
import md.koloritmarketplace.model.entity.account.AccountEntity;
import md.koloritmarketplace.model.enums.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "bucket", schema = "marketplace")
@PrimaryKeyJoinColumn(name = "bucketid")
public class BucketEntity extends AppEntity {
    @Column(name = "bucketid", insertable = false, updatable = false)
    private Long bucketId;

    @ManyToOne
    @JoinColumn(name = "accountid", referencedColumnName = "accountid")
    private AccountEntity buyer;

    @Column(name = "ordercode")
    private String orderCode;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "bucketstatus")
    @Enumerated(EnumType.STRING)
    private BuckeStatusEnum bucketStatus;

    @Column(name = "typedelivery")
    @Enumerated(EnumType.STRING)
    private TypeDeliveryEnum typeDelivery;

    @Column(name = "typepay")
    @Enumerated(EnumType.STRING)
    private TypePayEnum typePay;

    @OneToMany(mappedBy = "bucketId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    Set<BucketContentEntity> content = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountaddressid", referencedColumnName = "accountaddressid")
    private AccountAddressEntity address;

    @Transient
    public ObjectTypeEnum getObjectTypeEnum() {
        return ObjectTypeEnum.BUCKET;
    }
}
