package com.opencode.centralbankparser.data.entities;

import com.opencode.centralbankparser.references.entities.ChangeTypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BIC_DIRECTORY_ENTRY")
@NoArgsConstructor
@Setter
@Getter
public class BicDirectoryEntryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_BIC_DIRECTORY_ENTRY")
    private Long idBicDirectoryEntry;

    @Column(name = "BIC", length = 9, nullable = false)
    private String bic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CHANGE_TYPE")
    private ChangeTypeEntity changeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ED")
    private Ed807Entity ed807;

    @OneToMany(mappedBy = "bicDirectoryEntryEntity")
    private List<SwbicsEntity> swibcsEntities = new ArrayList<>();

    @OneToMany(mappedBy = "bicDirectoryEntry")
    private List<AccountsEntity> accounts = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "ID_PARTICIPANT_INFO")
    private ParticipantInfoEntity participantInfoEntity;
}

