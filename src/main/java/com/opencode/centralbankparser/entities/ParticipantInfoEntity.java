package com.opencode.centralbankparser.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "participant_info")
@NoArgsConstructor
@Getter
@Setter
public class ParticipantInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_participant_info")
    public Long idParticipantInfo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_bic_directory_entry", nullable = false)
    private BicDirectoryEntryEntity bicDirectoryEntry;

    @Column(name = "name_p", length = 160, nullable = false)
    private String nameP;

    @Column(name = "eng_name", length = 140)
    private String engName;

    @Column(name = "reg_n", length = 9)
    private String regN;

    @Column(name = "cntr_cd", length = 2)
    private String cntrCd;

    @Column(name = "rgn", length = 2, nullable = false)
    private String rgn;

    @Column(name = "ind", length = 6)
    private String ind;

    @Column(name = "tnp", length = 5)
    private String tnp;

    @Column(name = "nnp", length = 25)
    private String nnp;

    @Column(name = "adr", length = 260)
    private String adr;

    @Column(name = "prnt_bic", length = 9)
    private String prntBic;

    @Column(name = "date_in", nullable = false)
    private Timestamp dateIn;

    @Column(name = "date_out")
    private Timestamp dateOut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pt_type", nullable = false)
    private PtTypeEntity ptType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_srvcs", nullable = false)
    private SrvcsEntity srvcs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_xch_type", nullable = false)
    private XchTypeEntity xchType;

    @Column(name = "uid", length = 10, nullable = false)
    private String uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_participant_status", nullable = false)
    private ParticipantStatusEntity participantStatus;

}

