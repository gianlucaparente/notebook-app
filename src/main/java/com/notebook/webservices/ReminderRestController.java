package com.notebook.webservices;

import com.notebook.persistence.Reminder;
import com.notebook.repository.ReminderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/reminders")
public class ReminderRestController {

	private final ReminderRepository reminderRepository;

	ReminderRestController(ReminderRepository reminderRepository) {
		this.reminderRepository = reminderRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	Collection<Reminder> getReminders() {
		System.out.println("ReminderRestController :: reminders '/reminders' called.");
		return this.reminderRepository.findAll();
	}

}
