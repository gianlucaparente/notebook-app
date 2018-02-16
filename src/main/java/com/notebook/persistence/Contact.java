package com.notebook.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=true)
    private String surname;

    @Column(nullable=true)
    private String telephone1;

    @Column(nullable=true)
    private String telephone2;

    public Contact(Long id, String name, String surname, String telephone1, String telephone2) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
    }

}
