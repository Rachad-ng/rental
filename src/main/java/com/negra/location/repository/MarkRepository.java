package com.negra.location.repository;

import com.negra.location.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Long> {

    @Query("SELECT M FROM Mark M ORDER BY M.libelle")
    List<Mark> getAllMarkOrderedByLibelle();

}