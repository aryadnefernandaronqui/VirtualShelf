package virtualShelf.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateUser(
        @NotBlank
        @Length(min = 3, max = 40)
        String name,
        @NotBlank
        @Length(min = 3, max = 60)
        String email,
        @NotBlank
        @Length(min = 3, max = 20)
        String password
) {
}
