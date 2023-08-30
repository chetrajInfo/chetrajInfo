package com.library.management.system.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name= "borrowedbook")
public class BorrowedBook {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;


    @Column(name="book_name")
    private String bookName;

    @Column(name="borrowed_date")
    private Date borrowedDate;
}
