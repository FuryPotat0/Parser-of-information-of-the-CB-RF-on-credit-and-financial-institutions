package com.opencode.centralbankparser.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PART_INFO")
@NoArgsConstructor
@Setter
@Getter
public class PartInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PART_INFO")
    private Long idPartInfo;

    @Column(name = "PART_NO", length = 6, nullable = false)
    private String partNo;

    @Column(name = "PART_QUANTITY", length = 6, nullable = false)
    private String partQuantity;

    @Column(name = "PART_AGGREGATE_ID", length = 27, nullable = false)
    private String partAggregateId;
}

