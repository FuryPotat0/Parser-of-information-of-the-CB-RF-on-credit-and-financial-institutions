package com.opencode.centralbankparser.references.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CHANGE_TYPE")
@NoArgsConstructor
@Getter
@Setter
public class ChangeTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CHANGE_TYPE")
    private Long idChangeType;

    @Column(name = "CODE", nullable = false, unique = true, length = 4)
    private String code;

    @Column(name = "NAME", length = 300, nullable = false)
    private String name;
}

