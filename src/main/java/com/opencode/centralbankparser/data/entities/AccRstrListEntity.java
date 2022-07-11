package com.opencode.centralbankparser.data.entities;

import com.opencode.centralbankparser.references.entities.AccRstrEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ACC_RSTR_LIST")
@NoArgsConstructor
@Getter
@Setter
public class AccRstrListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ACC_RSTR_LIST")
    public Long idAccRstrList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ACC_RSTR", nullable = false)
    private AccRstrEntity accRstr;

    @Column(name = "ACC_RSTR_DATE", nullable = false)
    private Timestamp accRstrDate;

    @Column(name = "SUCCESSOR_BIC", length = 9)
    private String successorBic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ACCOUNTS")
    private AccountsEntity accounts;

}

