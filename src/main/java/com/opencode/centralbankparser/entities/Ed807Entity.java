package com.opencode.centralbankparser.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ed807")
@Setter
@Getter
@NoArgsConstructor
public class Ed807Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_ed;

    @Column(name = "ed_no", length = 9, nullable = false)
    private String edNo;

    @Column(name = "ed_date", nullable = false)
    private Timestamp edDate;

    @Column(name = "ed_author", length = 10, nullable = false)
    private String edAuthor;

    @Column(name = "ed_receiver", length = 10)
    private String edReceiver;

    @ManyToOne
    @JoinColumn(name = "id_creation_reason", nullable = false)
    private CreationReasonEntity creationReasonEntity;

    @Column(name = "creation_date_time", nullable = false)
    private Timestamp creationDateTime;

    @ManyToOne
    @JoinColumn(name = "id_info_type_code")
    private InfoTypeCodeEntity infoTypeCodeEntity;

    @Column(name = "business_day", nullable = false)
    private Timestamp businessDay;

    @Column(name = "directory_version")
    private Integer directoryVersion;
}

