package com.notebook.notebookapp;

import com.notebook.persistence.Note;
import com.notebook.repository.NoteRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.stream.Stream;

@SpringBootApplication
@EntityScan("com.notebook.persistence")
@EnableJpaRepositories("com.notebook.repository")
@ComponentScan(basePackages = {"com.notebook"})
public class NotebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotebookApplication.class, args);
	}

	@Bean
	ApplicationRunner run(NoteRepository cr) {
		return args ->
				Stream.of("Note1", "Note2")
						.forEach(x -> cr.save(new Note(null, x)));

	}

}

