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

	@RequestMapping(method = RequestMethod.GET)
	Collection<Contact> getContacts() {
		System.out.println("ContactRestController :: endpoint '/contacts' called.");
		return this.contactRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	Contact getContactById(@PathVariable Long id) {
		System.out.println("ContactRestController :: endpoint '/contacts/" + id + "' called.");
		return this.contactRepository.findOne(id);
	}

    @RequestMapping(value="/contact", method=RequestMethod.POST)
    Contact insertContact(@RequestBody Contact contact) {
        return this.contactRepository.save(contact);
    }

    @RequestMapping(value="/contact", method=RequestMethod.PUT)
    Contact updateContact(@RequestBody Contact contact) {
        return this.contactRepository.save(contact);
    }

}
