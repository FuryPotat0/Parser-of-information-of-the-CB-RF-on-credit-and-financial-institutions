package com.opencode.centralbankparser.data.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.opencode.centralbankparser.references.entities.CreationReasonEntity;
import com.opencode.centralbankparser.references.entities.InfoTypeCodeEntity;
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
    @Column(name = "id_ed")
    private Long idEd;

    @Column(name = "ed_no", length = 9, nullable = false)
    @JacksonXmlProperty(localName = "EDNo")
    private String edNo;

    @Column(name = "ed_date", nullable = false)
    @JacksonXmlProperty(localName = "EDDate")
    private Timestamp edDate;

    @Column(name = "ed_author", length = 10, nullable = false)
    @JacksonXmlProperty(localName = "EDAuthor")
    private String edAuthor;

    @Column(name = "ed_receiver", length = 10)
    @JacksonXmlProperty(localName = "EDReceiver")
    private String edReceiver;

    @ManyToOne
    @JoinColumn(name = "id_creation_reason", nullable = false)
    private CreationReasonEntity creationReasonEntity;

    @Column(name = "creation_date_time", nullable = false)
    @JacksonXmlProperty(localName = "CreationDateTime")
    private Timestamp creationDateTime;

    @ManyToOne
    @JoinColumn(name = "id_info_type_code")
    private InfoTypeCodeEntity infoTypeCodeEntity;

    @Column(name = "business_day", nullable = false)
    @JacksonXmlProperty(localName = "BusinessDay")
    private Timestamp businessDay;

    @Column(name = "directory_version")
    @JacksonXmlProperty(localName = "DirectoryVersion")
    private Integer directoryVersion;
}

