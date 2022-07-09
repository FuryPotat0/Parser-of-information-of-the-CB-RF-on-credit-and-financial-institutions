package com.opencode.centralbankparser.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "xch_type")
@NoArgsConstructor
@Getter
@Setter
public class XchTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_xch_type")
    private Long idXchType;

    @Column(name = "code", nullable = false, unique = true)
    private Character code;

    @Column(name = "name", length = 300, nullable = false)
    private String name;
}

