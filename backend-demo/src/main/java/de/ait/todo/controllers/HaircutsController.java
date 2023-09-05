package de.ait.todo.controllers;

import de.ait.todo.controllers.api.HaircutsApi;
import de.ait.todo.dto.NewHaircutDto;
import de.ait.todo.dto.HaircutDto;
import de.ait.todo.dto.HaircutsPage;
import de.ait.todo.security.details.AuthenticatedUser;
import de.ait.todo.services.HaircutsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HaircutsController implements HaircutsApi {

    private final HaircutsService tasksService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<HaircutsPage> getAll() {
        return ResponseEntity
                .ok(tasksService.getAll());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<HaircutDto> getById(Long taskId) {
        return ResponseEntity.ok(tasksService.getById(taskId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void deleteTask(Long taskId) {
        tasksService.deleteTask(taskId);
    }

    @PreAuthorize("hasAuthority('USER')")
    @Override
    public ResponseEntity<HaircutDto> addTask(AuthenticatedUser authenticatedUser, NewHaircutDto task) {
        Long currentUserId = authenticatedUser.getUser().getId();
        return ResponseEntity.status(201)
                .body(tasksService.addTask(currentUserId, task));
    }
}
