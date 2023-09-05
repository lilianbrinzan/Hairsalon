package de.ait.todo.services;

import de.ait.todo.dto.NewHaircutDto;
import de.ait.todo.dto.HaircutDto;
import de.ait.todo.dto.HaircutsPage;

public interface HaircutsService {
    HaircutsPage getAll();

    HaircutDto getById(Long taskId);

    void deleteTask(Long taskId);

    HaircutDto addTask(Long currentUserId, NewHaircutDto task);
}
