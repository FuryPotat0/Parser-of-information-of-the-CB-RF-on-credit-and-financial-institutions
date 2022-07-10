package com.opencode.centralbankparser.data.entities;

import com.opencode.centralbankparser.references.entities.AccountStatusEntity;
import com.opencode.centralbankparser.references.entities.RegulationAccountTypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@Getter
@Setter
public class AccountsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_accounts")
    private Long idAccounts;

    @Column(name = "account", length = 20, nullable = false)
    private String account;

    @Column(name = "ck", length = 2)
    private String ck;

    @ManyToOne
    @JoinColumn(name = "id_account_status")
    private AccountStatusEntity accountStatus;

    @ManyToOne
    @JoinColumn(name = "id_regulation_account_type")
    private RegulationAccountTypeEntity regulationAccountType;

    @Column(name = "account_cbrbic", length = 9, nullable = false)
    private String accountCbrbic;

    @Column(name = "date_in", nullable = false)
    private Timestamp dateIn;

    @Column(name = "date_out")
    private Timestamp dateOut;

    @ManyToOne
    @JoinColumn(name = "id_bic_directory_entry")
    private BicDirectoryEntryEntity bicDirectoryEntry;

    @OneToMany(mappedBy = "accounts")
    private List<AccRstrListEntity> accRsrtListEntities = new ArrayList<>();
}

