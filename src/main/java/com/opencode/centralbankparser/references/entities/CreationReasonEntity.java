package com.opencode.centralbankparser.references.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "creation_reason")
@Setter
@Getter
@NoArgsConstructor
public class CreationReasonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CREATION_REASON")
    private Long idCreationReason;

    @Column(name = "CODE", nullable = false, unique = true, length = 4)
    private String code;

    @Column(name = "NAME", length = 500, nullable = false)
    private String name;
}

