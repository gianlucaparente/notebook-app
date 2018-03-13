package com.notebook.webservices;

import com.notebook.persistence.Contact;
import com.notebook.repository.ContactRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/contacts")
public class ContactRestController {

	private final ContactRepository contactRepository;

	ContactRestController(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	Collection<Contact> getContacts() {
		System.out.println("ContactRestController :: endpoint '/contacts' called.");
		return this.contactRepository.findAll();
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value="/search")
	Collection<Contact> getContacts(@RequestParam String query) {
		System.out.println("ContactRestController :: endpoint '/contacts/search' called with param query: " + query);
		return this.contactRepository.findByNameContaining(query);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	Contact getContactById(@PathVariable Long id) {
		System.out.println("ContactRestController :: endpoint '/contacts/" + id + "' called.");
		return this.contactRepository.findOne(id);
	}

	@CrossOrigin
    @RequestMapping(value="/contact", method=RequestMethod.POST)
    Contact insertContact(@RequestBody Contact contact) {
        return this.contactRepository.save(contact);
    }

	@CrossOrigin
    @RequestMapping(value="/contact", method=RequestMethod.PUT)
    Contact updateContact(@RequestBody Contact contact) {
        return this.contactRepository.save(contact);
    }

}
