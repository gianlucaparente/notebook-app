package com.notebook.webservices;

import com.notebook.persistence.Note;
import com.notebook.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/notes")
public class NoteRestController {

	private final NoteRepository noteRepository;

	NoteRestController(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	Collection<Note> getNotes() {
		System.out.println("NoteRestController :: endpoint '/notes' called.");
		return this.noteRepository.findAll(new Sort(Sort.Direction.DESC, "date"));
	}

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	Note getNoteById(@PathVariable Long id) {
		System.out.println("NoteRestController :: endpoint '/notes/" + id + "' called.");
		return this.noteRepository.findOne(id);
	}

    @RequestMapping(value="/note", method=RequestMethod.POST)
    Note insertNote(@RequestBody Note note) {
        return this.noteRepository.save(note);
    }

    @RequestMapping(value="/note", method=RequestMethod.PUT)
    Note updateNote(@RequestBody Note note) {
        return this.noteRepository.save(note);
    }

}
