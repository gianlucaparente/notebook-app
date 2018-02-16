package com.notebook.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.notebook.util.Frequency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;

@Entity
@NoArgsConstructor
@Data
public class Reminder {

    @Id
    @GeneratedValue
    protected Long id;

    @Column
    private Frequency frequency;

    @Column
    protected Time date;

}



