package com.opencode.centralbankparser.data.entities;

import com.opencode.centralbankparser.references.entities.AccountStatusEntity;
import com.opencode.centralbankparser.references.entities.RegulationAccountTypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ACCOUNTS")
@NoArgsConstructor
@Getter
@Setter
public class AccountsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ACCOUNTS")
    private Long idAccounts;

    @Column(name = "ACCOUNT", length = 20, nullable = false)
    private String account;

    @Column(name = "CK", length = 2)
    private String ck;

    @ManyToOne
    @JoinColumn(name = "ID_ACCOUNT_STATUS")
    private AccountStatusEntity accountStatus;

    @ManyToOne
    @JoinColumn(name = "ID_REGULATION_ACCOUNT_TYPE")
    private RegulationAccountTypeEntity regulationAccountType;

    @Column(name = "ACCOUNT_CBRBIC", length = 9, nullable = false)
    private String accountCbrbic;

    @Column(name = "DATE_IN", nullable = false)
    private Timestamp dateIn;

    @Column(name = "DATE_OUT")
    private Timestamp dateOut;

    @ManyToOne
    @JoinColumn(name = "ID_BIC_DIRECTORY_ENTRY")
    private BicDirectoryEntryEntity bicDirectoryEntry;
}

