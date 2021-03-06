package com.notebook.repository;

import com.notebook.persistence.Note;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByExpired(boolean expired, Sort sort);
    List<Note> findByExpiredAndDate(boolean expired, Date date, Sort sort);

}
