package com.opencode.centralbankparser.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "srvcs")
@NoArgsConstructor
@Getter
@Setter
public class SrvcsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_srvcs")
    private Long idSrvcs;

    @Column(name = "code", nullable = false, unique = true)
    private Character code;

    @Column(name = "name", length = 300, nullable = false)
    private String name;
}

