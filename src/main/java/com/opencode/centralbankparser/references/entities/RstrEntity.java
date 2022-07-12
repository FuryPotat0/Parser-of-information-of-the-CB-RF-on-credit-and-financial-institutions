package com.opencode.centralbankparser.references.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "RSTR")
@NoArgsConstructor
@Getter
@Setter
public class RstrEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_RSTR")
    private Long idRstr;

    @Column(name = "CODE", nullable = false, unique = true, length = 4)
    private String code;

    @Column(name = "NAME", length = 300, nullable = false)
    private String name;
}

