package com.opencode.centralbankparser.entities;

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
    private Long idRegulationAccountType;

    @Column(name = "code", length = 4, nullable = false)
    private String code;

    @Column(name = "name", length = 50, nullable = false)
    private String name;
}

