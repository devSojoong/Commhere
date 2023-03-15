package com.example.commhere.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "store")
public class Store {

    @Id
    @Column(name = "store_cd", nullable = false, length = 100)
    private String store;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "store_nm", nullable = false, length = 100)
    private String storeNm;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "availability", nullable = false)
    private int availability;

}
