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
    private Long idSrvcs;

    @Column(name = "code", nullable = false)
    private Character code;

    @Column(name = "name", length = 50, nullable = false)
    private String name;
}

