package com.example.commhere.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "menu")
public class Menu {

    @Id
    @Column(name = "menu_nm", nullable = false, length = 100)
    private String menuNm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_cd")
    private Store store;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "availability", nullable = false)
    private String availability;

    /**
     * insert 되기 전 실행되는 로직.(persist 되기 전)
     */
    @PrePersist
    public void prePersist(){
        this.availability = this.availability == null ? "Y" : this.availability;
    }
}
