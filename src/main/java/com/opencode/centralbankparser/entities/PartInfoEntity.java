package com.opencode.centralbankparser.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "part_info")
@NoArgsConstructor
@Setter
@Getter
public class PartInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_part_info")
    private Long idPartInfo;

    @Column(name = "part_no", length = 6, nullable = false)
    private String partNo;

    @Column(name = "part_quantity", length = 6, nullable = false)
    private String partQuantity;

    @Column(name = "part_aggregate_id", length = 27, nullable = false)
    private String partAggregateId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Ed807Entity ed807;
}

