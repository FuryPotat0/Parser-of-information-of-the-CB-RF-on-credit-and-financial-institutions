package com.opencode.centralbankparser.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "swibcs")
@NoArgsConstructor
@Getter
@Setter
public class SwibcsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSwibcs;

    @Column(name = "swibcs", length = 11, nullable = false)
    private String swibcs;

    @Column(name = "default_swbic", nullable = false)
    private boolean defaultSwbic;

    @ManyToOne
    @JoinColumn(name = "id_bic_directory_entry")
    private BicDirectoryEntryEntity bicDirectoryEntryEntity;

}

