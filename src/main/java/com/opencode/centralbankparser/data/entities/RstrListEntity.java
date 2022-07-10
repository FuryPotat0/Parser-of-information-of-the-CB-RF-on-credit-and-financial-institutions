package com.opencode.centralbankparser.data.entities;

import com.opencode.centralbankparser.references.entities.RstrEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rstr_list")
@NoArgsConstructor
@Getter
@Setter
public class RstrListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rstr_list")
    public Long idRstrList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rstr", nullable = false)
    private RstrEntity rstr;

    @Column(name = "rstr_date", nullable = false)
    private Timestamp rstrDate;
}
