package virtualShelf.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UpdateBook(
        @Length(max = 100)
        String name,
        @Length(max = 100)
        String author,
        @Length(max = 100)
        String publisher,
        @Length(max = 50)
        String genre,
        @Length(max = 30)
        String language
) {
}
