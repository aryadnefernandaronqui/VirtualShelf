package virtualShelf.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

public record GetBooks(
        @NotNull
        UUID bookId,
        @NotNull
        UUID shelfId
) {
}
