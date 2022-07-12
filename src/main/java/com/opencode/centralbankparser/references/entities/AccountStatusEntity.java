package com.opencode.centralbankparser.references.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT_STATUS")
@NoArgsConstructor
@Getter
@Setter
public class AccountStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ACCOUNT_STATUS")
    public Long idAccountStatus;

    @Column(name = "CODE", nullable = false, unique = true, length = 4)
    private String code;

    @Column(name = "NAME", length = 300, nullable = false)
    private String name;
}

