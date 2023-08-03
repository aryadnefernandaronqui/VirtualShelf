package virtualShelf.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CreateShelf(@NotBlank String name) {
}
