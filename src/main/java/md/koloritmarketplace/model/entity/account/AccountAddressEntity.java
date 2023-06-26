package md.koloritmarketplace.model.entity.account;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "accountaddress", schema = "marketplace")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountAddressEntity {
    @Id
    @Column(name = "accountaddressid")
    @SequenceGenerator(name = "accountAddressGenerator", sequenceName = "marketplace.accountadress_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountAddressGenerator")
    private Long accountAddressId;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "region")
    private String region;

    @Column(name = "town")
    private String town;

    @Column(name = "adress")
    private String adress;

    @Column(name = "zipindex")
    private String zipIndex;

    @Column(name = "accountid")
    private Long accountId;

}
