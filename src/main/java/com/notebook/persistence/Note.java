package com.notebook.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Note {

	@Id
	@GeneratedValue
	private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private Date date;

    @Column(nullable = true)
    private String address;

    @OneToOne
    private Contact contact;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Reminder> reminders;

	public Note(Long id, String title) {
		this.id = id;
		this.title = title;
	}

    public Note(Long id, String title, String description, Date date, String address) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.address = address;
    }

    public Note(Long id, String title, String description, Date date, String address, Contact contact) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.address = address;
        this.contact = contact;
    }

    public Note(Long id, String title, String description, Date date, String address, Contact contact, List<Reminder> reminders) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.address = address;
        this.contact = contact;
        this.reminders = reminders;
    }

}
