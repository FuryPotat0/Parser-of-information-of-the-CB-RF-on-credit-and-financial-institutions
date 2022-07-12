package com.opencode.centralbankparser.data.entities;

import com.opencode.centralbankparser.references.entities.RstrEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "RSTR_LIST")
@NoArgsConstructor
@Getter
@Setter
public class RstrListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_RSTR_LIST")
    public Long idRstrList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_RSTR", nullable = false)
    private RstrEntity rstr;

    @Column(name = "RSTR_DATE", nullable = false)
    private Timestamp rstrDate;

    @ManyToOne
    @JoinColumn(name = "ID_PARTICIPANT_INFO")
    private ParticipantInfoEntity participantInfoEntity;
}

