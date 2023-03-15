package com.example.commhere.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "favor")
public class Favor {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "favor_id", nullable = false)
    private Long favorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_cd")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
