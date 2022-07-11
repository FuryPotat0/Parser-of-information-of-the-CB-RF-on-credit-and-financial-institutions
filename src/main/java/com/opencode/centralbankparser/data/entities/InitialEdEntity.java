package com.opencode.centralbankparser.data.entities;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_INITIAL_ED")
    private Long idInitialEd;

    @Column(name = "ED_NO", length = 9, nullable = false)
    private String edNo;

    @Column(name = "ED_DATE", nullable = false)
    private Timestamp edDate;

    @Column(name = "ED_AUTHOR", length = 10, nullable = false)
    private String edAuthor;
}

