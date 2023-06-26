package md.koloritmarketplace.model.entity.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import md.koloritmarketplace.model.entity.AppEntity;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account", schema = "marketplace")
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "accountid")
public class AccountEntity extends AppEntity {
    @Column(name = "accountid", insertable = false, updatable = false)
    private Long accountId;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "fio")
    private String fio;

    @Column(name = "pass")
    private String password;

    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "linkaccountrole", schema = "marketplace",
            joinColumns = {
                    @JoinColumn(name = "accountid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "accountroleid")
            }
    )
    private Set<AccountRoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "accountId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<AccountAddressEntity> contacts;

    @Transient
    public ObjectTypeEnum getObjectTypeEnum() {
        return ObjectTypeEnum.ACCOUNT;
    }
}
