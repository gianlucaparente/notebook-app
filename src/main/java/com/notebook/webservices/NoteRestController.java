package com.notebook.webservices;

import com.notebook.persistence.Note;
import com.notebook.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import javax.persistence.Query;
import javax.xml.bind.DatatypeConverter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notes")
public class NoteRestController {

	private final NoteRepository noteRepository;

	NoteRestController(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	Collection<Note> getNotes() {
		System.out.println("NoteRestController :: endpoint '/notes' called.");
		return this.noteRepository.findAll();
	}

	@CrossOrigin
	@RequestMapping(value="expired/{expired}", method = RequestMethod.GET)
	@OrderBy("date")
	Collection<Note> getNotes(@PathVariable boolean expired) {
		System.out.println("NoteRestController :: endpoint '/notes/expired' with param " + expired + " called.");

		Collection<Note> notes = this.noteRepository.findByExpired(expired, new Sort(Sort.Direction.ASC, "date"));

		Collection<Note> notesWithDate = notes.stream().filter(note -> note.getDate() != null).collect(Collectors.toList());
		Collection<Note> notesWithoutDate = notes.stream().filter(note -> note.getDate() == null).collect(Collectors.toList());

		notesWithDate.addAll(notesWithoutDate);

		return notesWithDate;
	}

	@CrossOrigin
	@RequestMapping(value="expired/{expired}/date/{date}", method = RequestMethod.GET)
	@OrderBy("date")
	Collection<Note> getNotes(@PathVariable boolean expired, @PathVariable String date) {
		System.out.println("NoteRestController :: endpoint '/notes/expired/date' with param " + expired + " and " + date + " called.");

		// Retrieve notes by expire
		List<Note> notes = this.noteRepository.findByExpired(expired, new Sort(Sort.Direction.ASC, "date"));

		// Calculate start and end based on day
		Calendar calendar = DatatypeConverter.parseDateTime(date);

		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);

		Date start = calendar.getTime();

		calendar.set(Calendar.HOUR, 24);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		Date end = calendar.getTime();

		// Filter by start and end dates
		notes = notes.stream().filter(note -> note.getDate().after(start) && note.getDate().before(end)).collect(Collectors.toList());

		return notes;
	}

	@CrossOrigin
	@RequestMapping(value="note/{id}", method = RequestMethod.GET)
	Note getNoteById(@PathVariable Long id) {
		System.out.println("NoteRestController :: endpoint '/notes/" + id + "' called.");
		return this.noteRepository.findOne(id);
	}

	@CrossOrigin
    @RequestMapping(value="/note", method=RequestMethod.POST)
    Note insertNote(@RequestBody Note note) {
		System.out.println("NoteRestController :: endpoint '/notes/note' for insert a note");
		note.setCreationDate(new Date());
		note.setExpired(note.isExpired());
		return this.noteRepository.save(note);
    }

	@CrossOrigin
    @RequestMapping(value="/note", method=RequestMethod.PUT)
    Note updateNote(@RequestBody Note note) {
        return this.noteRepository.save(note);
    }

	@CrossOrigin
	@RequestMapping(value="/note/{id}", method=RequestMethod.DELETE)
	void deleteNote(@PathVariable Long id) {
		System.out.println("NoteRestController :: endpoint '/notes/note' for delete a note");
		this.noteRepository.delete(id);
	}

}
