package com.opencode.centralbankparser.data.entities;

import com.opencode.centralbankparser.references.entities.ChangeTypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bic_directory_entry")
@NoArgsConstructor
@Setter
@Getter
public class BicDirectoryEntryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_bic_directory_entry")
    private Long idBicDirectoryEntry;

    @Column(name = "bic", length = 9, nullable = false)
    private String bic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_change_type")
    private ChangeTypeEntity changeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ed")
    private Ed807Entity ed807;

    @OneToMany(mappedBy = "bicDirectoryEntryEntity")
    private List<SwbicsEntity> swibcsEntities = new ArrayList<>();

    @OneToMany(mappedBy = "bicDirectoryEntry")
    private List<AccountsEntity> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "bicDirectoryEntry")
    private List<ParticipantInfoEntity> participantInfoEntities = new ArrayList<>();
}

