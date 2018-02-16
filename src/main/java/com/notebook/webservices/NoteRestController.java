package com.notebook.webservices;

import com.notebook.persistence.Note;
import com.notebook.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
		return this.noteRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	Note getNoteById(@PathVariable Long id) {
		System.out.println("NoteRestController :: endpoint '/note/" + id + "' called.");
		return this.noteRepository.findOne(id);
	}

//	@RequestMapping(value="/error")
//    String error() {
//		System.out.println("NoteRestController :: error");
//        return "Error";
//    }

//	@Override
//	public String getErrorPath() {
//		return PATH;
//	}

//    @RequestMapping(value="/note", method=RequestMethod.POST)
//    Note insertNote(@RequestBody Note note) {
//        return this.noteRepository.save(note);
//    }
//
//    @RequestMapping(value="/note", method=RequestMethod.PUT)
//    Note updateNote(@RequestBody Note note) {
//        return this.noteRepository.save(note);
//    }

}
