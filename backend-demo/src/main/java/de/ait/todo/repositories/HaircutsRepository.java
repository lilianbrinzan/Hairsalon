package de.ait.todo.repositories;

import de.ait.todo.models.Haircut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HaircutsRepository extends JpaRepository<Haircut, Long> {
    List<Haircut> findAllByUser_Id(Long userId);
}
