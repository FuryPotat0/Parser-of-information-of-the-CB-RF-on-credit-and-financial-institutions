package com.opencode.centralbankparser.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "acc_rstr_list")
@NoArgsConstructor
@Getter
@Setter
public class AccRstrListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long idAccRstrList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_acc_rstr", nullable = false)
    private AccRstrEntity accRstr;

    @Column(name = "acc_rstr_date", nullable = false)
    private Timestamp accRstrDate;

    @Column(name = "successor_bic", length = 9)
    private String successorBic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_accounts")
    private AccountsEntity accounts;

}

