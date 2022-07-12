package com.opencode.centralbankparser.references.entities;

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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_INFO_TYPE_CODE")
    private Long idInfoTypeCode;

    @Column(name = "CODE", nullable = false, unique = true, length = 4)
    private String code;

    @Column(name = "NAME", length = 300, nullable = false)
    private String name;
}

