package com.opencode.centralbankparser.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "acc_rstr")
@NoArgsConstructor
@Getter
@Setter
public class AccRstrEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long idAccRstr;

    @Column(name = "code", length = 4, nullable = false)
    private String code;

    @Column(name = "name", length = 50, nullable = false)
    private String name;
}

