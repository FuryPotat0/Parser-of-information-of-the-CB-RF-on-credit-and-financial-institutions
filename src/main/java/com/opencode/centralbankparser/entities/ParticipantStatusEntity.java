package com.opencode.centralbankparser.entities;

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
    private Long idParticipantStatus;

    @Column(name = "code", length = 4, nullable = false)
    private String code;

    @Column(name = "name", length = 50, nullable = false)
    private String name;
}

