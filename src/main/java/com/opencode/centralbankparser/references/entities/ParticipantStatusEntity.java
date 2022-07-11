package com.opencode.centralbankparser.references.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "participant_status")
@NoArgsConstructor
@Getter
@Setter
public class ParticipantStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_participant_status")
    private Long idParticipantStatus;

    @Column(name = "code", nullable = false, unique = true, length = 4)
    private String code;

    @Column(name = "name", length = 300, nullable = false)
    private String name;
}

