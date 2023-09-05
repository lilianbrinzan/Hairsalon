package de.ait.todo.controllers.api;

import de.ait.todo.dto.NewHaircutDto;
import de.ait.todo.dto.HaircutDto;
import de.ait.todo.dto.HaircutsPage;
import de.ait.todo.security.details.AuthenticatedUser;
import de.ait.todo.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tags(value = {
        @Tag(name = "Haircuts")})
@RequestMapping("/api/tasks")
@ApiResponse(responseCode = "403", description = "Пользователь не аутентифицирован",
        content = {
                @Content(mediaType = "application/json",
                        schema = @Schema(ref = "StandardResponseDto"))
        }
)
public interface HaircutsApi {

    @Operation(summary = "Получение списка всех задач", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с задачами",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HaircutsPage.class))
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "StandardResponseDto"))
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Запрещено",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "StandardResponseDto"))
                    }
            )
    })
    @GetMapping
    ResponseEntity<HaircutsPage> getAll();

    @Operation(summary = "Получение задачи", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HaircutDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Не найдено",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "StandardResponseDto"))
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "StandardResponseDto"))
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Запрещено",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "StandardResponseDto"))
                    }
            )
    })
    @GetMapping("/{task-id}")
    ResponseEntity<HaircutDto> getById(@Parameter(description = "идентификатор задачи") @PathVariable("task-id") Long taskId);


    @Operation(summary = "Удаление задачи", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Задача удалена"
            ),
            @ApiResponse(responseCode = "404", description = "Не найдено",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "StandardResponseDto"))
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "StandardResponseDto"))
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Запрещено",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "StandardResponseDto"))
                    }
            )
    })
    @DeleteMapping("/{task-id}")
    void deleteTask(@PathVariable("task-id") Long taskId);

    @Operation(summary = "Добавление задачи", description = "Доступно только пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Созданная задача",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HaircutDto.class))
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class))
                    })
    })
    @PostMapping
    ResponseEntity<HaircutDto> addTask(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser user,
                                       @RequestBody @Valid NewHaircutDto task);
}
