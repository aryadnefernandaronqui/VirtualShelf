package virtualShelf.dtos;

import jakarta.validation.constraints.NotBlank;

public record Login(
        @NotBlank
        String email,
        @NotBlank
        String password
) {


}
