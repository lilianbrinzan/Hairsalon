package de.ait.todo.services.impl;

import de.ait.todo.dto.NewHaircutDto;
import de.ait.todo.dto.HaircutDto;
import de.ait.todo.dto.HaircutsPage;
import de.ait.todo.exceptions.RestException;
import de.ait.todo.models.Haircut;
import de.ait.todo.models.User;
import de.ait.todo.repositories.HaircutsRepository;
import de.ait.todo.repositories.UsersRepository;
import de.ait.todo.services.HaircutsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static de.ait.todo.dto.HaircutDto.from;

@RequiredArgsConstructor
@Service
public class HaircutsServiceImpl implements HaircutsService {

    private final HaircutsRepository tasksRepository;

    private final UsersRepository usersRepository;

    @Override
    public HaircutsPage getAll() {
        return HaircutsPage.builder()
                .tasks(from(tasksRepository.findAll()))
                .build();
    }

    @Override
    public HaircutDto getById(Long taskId) {
        Haircut task = tasksRepository.findById(taskId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "Задача <" + taskId + "> не найдена"));

        return from(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        if (tasksRepository.existsById(taskId)) {
            tasksRepository.deleteById(taskId);
        } else {
            throw new RestException(HttpStatus.NOT_FOUND, "Задача <" + taskId + "> не найдена");
        }
    }

    @Override
    public HaircutDto addTask(Long currentUserId, NewHaircutDto task) {
        User user = usersRepository.findById(currentUserId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Пользователь <" + currentUserId + "> не найден"));

        Haircut newTask = Haircut.builder()
                .name(task.getName())
                .description(task.getDescription())
                .user(user)
                .build();

        tasksRepository.save(newTask);

        return from(newTask);
    }
}
