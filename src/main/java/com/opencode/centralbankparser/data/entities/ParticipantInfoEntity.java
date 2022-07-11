package com.opencode.centralbankparser.data.entities;

import com.opencode.centralbankparser.references.entities.ParticipantStatusEntity;
import com.opencode.centralbankparser.references.entities.PtTypeEntity;
import com.opencode.centralbankparser.references.entities.SrvcsEntity;
import com.opencode.centralbankparser.references.entities.XchTypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PARTICIPANT_INFO")
@NoArgsConstructor
@Getter
@Setter
public class ParticipantInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PARTICIPANT_INFO")
    public Long idParticipantInfo;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "ID_BIC_DIRECTORY_ENTRY", nullable = false)
//    private BicDirectoryEntryEntity bicDirectoryEntry;

    @Column(name = "NAME_P", length = 160, nullable = false)
    private String nameP;

    @Column(name = "ENG_NAME", length = 140)
    private String engName;

    @Column(name = "REG_N", length = 9)
    private String regN;

    @Column(name = "CNTR_CD", length = 2)
    private String cntrCd;

    @Column(name = "RGN", length = 2, nullable = false)
    private String rgn;

    @Column(name = "IND", length = 6)
    private String ind;

    @Column(name = "TNP", length = 5)
    private String tnp;

    @Column(name = "NNP", length = 27)
    private String nnp;

    @Column(name = "ADR", length = 260)
    private String adr;

    @Column(name = "PRNT_BIC", length = 9)
    private String prntBic;

    @Column(name = "DATE_IN", nullable = false)
    private Timestamp dateIn;

    @Column(name = "DATE_OUT")
    private Timestamp dateOut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PT_TYPE", nullable = false)
    private PtTypeEntity ptType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SRVCS", nullable = false)
    private SrvcsEntity srvcs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_XCH_TYPE", nullable = false)
    private XchTypeEntity xchType;

    @Column(name = "UID", length = 10, nullable = false)
    private String uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PARTICIPANT_STATUS", nullable = false)
    private ParticipantStatusEntity participantStatus;

    @OneToMany(mappedBy = "participantInfoEntity")
    private List<RstrListEntity> rstrListEntities = new ArrayList<>();
}

