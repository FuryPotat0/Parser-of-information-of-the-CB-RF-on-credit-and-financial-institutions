package com.opencode.centralbankparser.references.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PT_TYPE")
@NoArgsConstructor
@Getter
@Setter
public class PtTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PT_TYPE")
    private Long idPtType;

    @Column(name = "CODE", nullable = false, unique = true, length = 2)
    private String code;

    @Column(name = "NAME", length = 300, nullable = false)
    private String name;
}

