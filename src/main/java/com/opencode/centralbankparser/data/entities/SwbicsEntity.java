package com.opencode.centralbankparser.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SWBICS")
@NoArgsConstructor
@Getter
@Setter
public class SwbicsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_SWBICS")
    private Long idSwbics;

    @Column(name = "SWBICS", length = 11, nullable = false)
    private String swbics;

    @Column(name = "DEFAULT_SWBIC", nullable = false)
    private boolean defaultSwbic;

    @ManyToOne
    @JoinColumn(name = "ID_BIC_DIRECTORY_ENTRY")
    private BicDirectoryEntryEntity bicDirectoryEntryEntity;
}

