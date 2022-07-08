package com.opencode.centralbankparser.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "initial_ed")
@NoArgsConstructor
@Setter
@Getter
public class InitialEdEntity {
    @Id
    private Long idInitialEd;

    @Column(name = "ed_no", length = 9, nullable = false)
    private String edNo;

    @Column(name = "ed_date", nullable = false)
    private Timestamp edDate;

    @Column(name = "ed_author", length = 10, nullable = false)
    private String edAuthor;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Ed807Entity ed807;
}

