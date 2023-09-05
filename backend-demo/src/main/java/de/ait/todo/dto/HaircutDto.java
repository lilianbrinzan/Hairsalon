package de.ait.todo.dto;

import de.ait.todo.models.Haircut;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(description = "Задача")
public class HaircutDto {

    @Schema(description = "идентификатор задачи, не указывается при добавлении", example = "1")
    private Long id;

    @Schema(description = "Название задачи", example = "Task")
    private String name;

    @Schema(description = "Описание задачи", example = "Description of Task ")
    private String description;

    public static HaircutDto from(Haircut task) {
        return HaircutDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .build();
    }

    public static List<HaircutDto> from(List<Haircut> tasks) {
        return tasks.stream().map(HaircutDto::from).collect(Collectors.toList());
    }
}
