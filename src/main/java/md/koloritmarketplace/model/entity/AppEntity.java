package md.koloritmarketplace.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import md.koloritmarketplace.model.entity.account.AccountEntity;
import md.koloritmarketplace.model.enums.ObjectStatus;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "APP", schema = "marketplace")
@Inheritance(strategy = InheritanceType.JOINED)
public  class AppEntity {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "appGenerator", sequenceName = "marketplace.APP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appGenerator")
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ObjectStatus status;

    @Column(name = "createdtime")
    @CreationTimestamp
    private LocalDateTime createdTime;

    @Column(name = "updatedtime")
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @Column(name = "isall")
    private Integer isAll;

    @Column(name = "apptype")
    @Enumerated(EnumType.STRING)
    ObjectTypeEnum type;

    @ManyToOne
    @JoinColumn(name = "accountid", referencedColumnName = "accountid")
    AccountEntity accountEntity;

    @OneToMany(mappedBy = "appId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ImageEntity> images=new ArrayList<>();

//    @OneToMany(mappedBy = "appId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<ImageEntity> videos=new ArrayList<>();
}
