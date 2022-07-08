package com.opencode.centralbankparser.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "info_type_code")
@Setter
@Getter
@NoArgsConstructor
public class InfoTypeCodeEntity {
    @Id //TODO rename all id fields
    @Column(name = "id_info_type_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idInfoTypeCode;

    @Column(name = "code", length = 4, nullable = false)
    private String code;

    @Column(name = "name", length = 300, nullable = false)
    private String name;
}

