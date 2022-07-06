package com.opencode.centralbankparser.entities;

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
    private Long idChangeType;

    @Column(name = "code", length = 4, nullable = false)
    private String code;

    @Column(name = "name", length = 300, nullable = false)
    private String name;
}
