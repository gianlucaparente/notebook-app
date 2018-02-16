package com.notebook.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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

	public Note(Long id, String title) {
		this.id = id;
		this.title = title;
	}

    public Note(Long id, String title, String description, Date date, String address, Contact contact) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.address = address;
        this.contact = contact;
    }

}
