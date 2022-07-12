package com.opencode.centralbankparser.data.entities;

import com.opencode.centralbankparser.references.entities.CreationReasonEntity;
import com.opencode.centralbankparser.references.entities.InfoTypeCodeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ED807")
@Setter
@Getter
@NoArgsConstructor
public class Ed807Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ED")
    private Long idEd;

    @Column(name = "ED_NO", length = 9, nullable = false)
    private String edNo;

    @Column(name = "ED_DATE", nullable = false)
    private Timestamp edDate;

    @Column(name = "ED_AUTHOR", length = 10, nullable = false)
    private String edAuthor;

    @Column(name = "ED_RECEIVER", length = 10)
    private String edReceiver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CREATION_REASON", nullable = false)
    private CreationReasonEntity creationReasonEntity;

    @Column(name = "CREATION_DATE_TIME", nullable = false)
    private Timestamp creationDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INFO_TYPE_CODE", nullable = false)
    private InfoTypeCodeEntity infoTypeCodeEntity;

    @Column(name = "BUSINESS_DAY", nullable = false)
    private Timestamp businessDay;

    @Column(name = "DIRECTORY_VERSION")
    private String directoryVersion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PART_INFO")
    private PartInfoEntity partInfoEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INITIAL_ED")
    private InitialEdEntity initialEdEntity;

}

