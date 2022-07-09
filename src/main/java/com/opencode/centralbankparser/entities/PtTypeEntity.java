package com.opencode.centralbankparser.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pt_type")
@NoArgsConstructor
@Getter
@Setter
public class PtTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pt_type")
    private Long idPtType;

    @Column(name = "code", nullable = false, unique = true, length = 2)
    private String code;

    @Column(name = "name", length = 300, nullable = false)
    private String name;
}

