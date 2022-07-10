package com.opencode.centralbankparser.references.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "regulation_account_type")
@NoArgsConstructor
@Getter
@Setter
public class RegulationAccountTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_regulation_account_type")
    private Long idRegulationAccountType;

    @Column(name = "code", nullable = false, unique = true, length = 4)
    private String code;

    @Column(name = "name", length = 300, nullable = false)
    private String name;
}

