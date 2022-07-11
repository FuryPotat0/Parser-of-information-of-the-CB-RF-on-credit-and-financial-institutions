package com.opencode.centralbankparser.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "swbics")
@NoArgsConstructor
@Getter
@Setter
public class SwbicsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_swbics")
    private Long idSwbics;

    @Column(name = "swibcs", length = 11, nullable = false)
    private String swbics;

    @Column(name = "default_swbic", nullable = false)
    private boolean defaultSwbic;

    @ManyToOne
    @JoinColumn(name = "id_bic_directory_entry")
    private BicDirectoryEntryEntity bicDirectoryEntryEntity;

}

