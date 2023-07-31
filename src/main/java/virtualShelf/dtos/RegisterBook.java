package virtualShelf.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record RegisterBook(
        @NotBlank
        @Length(max = 100)
       String name,
       @NotBlank
       @Length(max = 100)
       String author,
       @NotBlank
        @Length(max = 100)
       String publisher,
       @NotBlank
        @Length(max = 50)
       String genre,
       @NotBlank
        @Length(max = 30)
       String language
) {
}
