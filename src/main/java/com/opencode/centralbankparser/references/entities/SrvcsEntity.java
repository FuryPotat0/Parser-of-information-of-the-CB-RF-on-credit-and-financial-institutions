package com.opencode.centralbankparser.references.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SRVCS")
@NoArgsConstructor
@Getter
@Setter
public class SrvcsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_SRVCS")
    private Long idSrvcs;

    @Column(name = "CODE", nullable = false, unique = true, length = 1)
    private String code;

    @Column(name = "NAME", length = 300, nullable = false)
    private String name;
}

