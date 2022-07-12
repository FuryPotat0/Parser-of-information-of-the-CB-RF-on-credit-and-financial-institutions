package com.opencode.centralbankparser.references.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "REGULATION_ACCOUNT_TYPE")
@NoArgsConstructor
@Getter
@Setter
public class RegulationAccountTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_REGULATION_ACCOUNT_TYPE")
    private Long idRegulationAccountType;

    @Column(name = "CODE", nullable = false, unique = true, length = 4)
    private String code;

    @Column(name = "NAME", length = 300, nullable = false)
    private String name;
}

