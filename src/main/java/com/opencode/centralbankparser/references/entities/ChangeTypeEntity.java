package com.opencode.centralbankparser.references.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "change_type")
@NoArgsConstructor
@Getter
@Setter
public class ChangeTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_change_type")
    private Long idChangeType;

    @Column(name = "code", nullable = false, unique = true, length = 4)
    private String code;

    @Column(name = "name", length = 300, nullable = false)
    private String name;
}

