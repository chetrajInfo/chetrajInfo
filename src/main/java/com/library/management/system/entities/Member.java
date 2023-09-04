package com.library.management.system.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="member")
public class Member {
    //member ID, name, list of borrowed books, and membership expiry date.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long member_id;

    @Column(name = "name")
    private String name;

    @NotBlank(message = "Phone number cannot be blank.") //blank field is not accepted for the phone
    @Pattern(regexp = "\\d{10}", message = "Invalid Phone Number Format.") // adding proper validation
    @Column(name="phone", unique = true) //making sure the phone number is unique and only used to register only once for the account
    //@Size(max = 10)
    //@NotNull
    private String phone;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BorrowedBook> borrowed_books;


    @Column(name="expiry_date")
    private LocalDate expiry_date;

}
