package md.koloritmarketplace.model.entity.account;

import lombok.*;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.enums.AccountRole;

import javax.persistence.*;

@Entity
@Table(name = "accountrole", schema = "marketplace")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountRoleEntity {

    @Id
    @Column(name = "accountroleid")
    @SequenceGenerator(name = "accountRoleGenerator", sequenceName = "marketplace.accountrole_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountRoleGenerator")
    private Long accountRoleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "code")
    private AccountRole accountRoleName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "ruvalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "envalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rovalue"))
    })
    private LanguageEmb languageEmb;


}
