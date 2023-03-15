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
@Table(name = "pay")
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Long payId;

    @Column(name = "items", nullable = false, length = 1000)
    private String items;

    @Column(name = "amt", nullable = false)
    private int amt;

    @Column(name = "option", nullable = false)
    private String option;

    @Column(name = "confirmation", nullable = false)
    private String confirmation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * insert 되기 전 실행되는 로직.(persist 되기 전)
     */
    @PrePersist
    public void prePersist(){
        this.confirmation = this.confirmation == null ? "Y" : this.confirmation;
    }
}
