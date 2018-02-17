package com.notebook.notebookapp;

import com.notebook.persistence.Contact;
import com.notebook.persistence.Note;
import com.notebook.persistence.Reminder;
import com.notebook.repository.ContactRepository;
import com.notebook.repository.NoteRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EntityScan("com.notebook.persistence")
@EnableJpaRepositories("com.notebook.repository")
@ComponentScan(basePackages = {"com.notebook"})
public class NotebookApplication {

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public static void main(String[] args) {
		SpringApplication.run(NotebookApplication.class, args);
	}

	@Bean
	ApplicationRunner run(
			ContactRepository contactRepository,
			NoteRepository noteRepository) {
		return args -> {

			List<Contact> contacts = this.createContacts(contactRepository);
			this.createNotes(contacts, noteRepository);

//			Stream.of("Note1", "Note2")
//					.forEach(x -> cr.save(new Note(null, x)));

		};
	}

	private List<Contact> createContacts(ContactRepository contactRepository) {

		List<Contact> contacts = new ArrayList<>();
		Contact contact;

		contact = new Contact(null, "WS GROUP Immobiliare", null, "+39 0392328670", null);
		contacts.add(contact);

		contact = new Contact(null, "Massimo", null, "+39 3388274192", null);
		contacts.add(contact);

		contact = new Contact(null, "TempoCasa", null, "039 831039", "3939528360");
		contacts.add(contact);

		contact = new Contact(null, "Filippo (Casa Monza)", null, "3474787491", null);
		contacts.add(contact);

		contacts.forEach(contactRepository::save);

		return contacts;

	}

	private List<Note> createNotes(List<Contact> contacts, NoteRepository cr) {

		List<Note> notes = new ArrayList<>();
		Note note;

		note = new Note(null, "Visita Casa", null, this.createDate(2018, 2, 16, 18, 30), "Via Masaccio 1, Monza", contacts.get(0));
		notes.add(note);

		note = new Note(null, "Promemoria Chiamare", null, this.createDate(2018, 2, 17, 0, 0), null, contacts.get(1));
		notes.add(note);

		List<Reminder> reminders = new ArrayList<>();
		reminders.add(new Reminder(null, this.createDate(2018, 2, 17, 9, 0)));
		reminders.add(new Reminder(null, this.createDate(2018, 2, 17, 18, 0)));
		note = new Note(null, "Visita Casa", null, this.createDate(2018, 2, 18, 15, 0), "Via Tosi 3, Monza", contacts.get(1), reminders);
		notes.add(note);

		note = new Note(null, "Visita Casa", null, this.createDate(2018, 2, 24, 14, 30), "Via Masaccio 1, Monza", contacts.get(0));
		notes.add(note);

		note = new Note(null, "Visita Casa", null, this.createDate(2018, 2, 24, 15, 30), "Via Giorgio De Chirico 4, Monza", contacts.get(2));
		notes.add(note);

		note = new Note(null, "Visita Casa", null, this.createDate(2018, 2, 25, 15, 0), "Via Sant'Elia 2, Monza", contacts.get(3));
		notes.add(note);

		notes.forEach(cr::save);

		return notes;
	}

	private Date createDate(int year, int month, int day, int hours, int minutes) {
		try {
			return this.simpleDateFormat.parse(day + "/" + month + "/" + year + " " + hours + ":" + minutes);
		} catch(ParseException pe) {
			return null;
		}
	}

}

