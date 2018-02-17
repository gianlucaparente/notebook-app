package com.notebook.persistence;

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
public class Reminder {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true)
    private Date date;

    public Reminder(Long id, Date date) {
        this.id = id;
        this.date = date;
    }

}
