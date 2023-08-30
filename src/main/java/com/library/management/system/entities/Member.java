package com.library.management.system.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="member")
public class Member {
    //member ID, name, list of borrowed books, and membership expiry date.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long member_id;

    @Column(name = "name")
    private String name;


    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name="borrowed_book")
    private List<BorrowedBook> borrowed_book;

    @Column(name="expiry_date")
    private Date expiry_date;

}
