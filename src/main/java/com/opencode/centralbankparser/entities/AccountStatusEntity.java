package com.opencode.centralbankparser.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "account_status")
@NoArgsConstructor
@Getter
@Setter
public class AccountStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_account_status")
    public Long idAccountStatus;

    @Column(name = "code", length = 4, nullable = false)
    private String code;

    @Column(name = "name", length = 300, nullable = false)
    private String name;
}

