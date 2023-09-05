package de.ait.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NewHaircutDto {

    @NotBlank
    @NotNull
    @Schema(description = "Название задачи", example = "Task")
    private String name;

    @NotBlank
    @NotNull
    @Schema(description = "Описание задачи", example = "Description of Task ")
    private String description;
}
