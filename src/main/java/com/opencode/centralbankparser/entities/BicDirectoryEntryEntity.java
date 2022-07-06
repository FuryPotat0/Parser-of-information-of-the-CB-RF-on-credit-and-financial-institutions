package com.opencode.centralbankparser.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bic_directory_entry")
@NoArgsConstructor
@Setter
@Getter
public class BicDirectoryEntryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBicDirectoryEntry;

    @Column(name = "bic", length = 9, nullable = false)
    private String bic;

    //change type

    @ManyToOne
    @JoinColumn(name = "id_ed")
    private Ed807Entity ed807;

}

