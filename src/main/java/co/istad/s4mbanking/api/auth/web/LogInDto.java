package co.istad.s4mbanking.api.auth.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LogInDto(@NotBlank
                       @Email String email,
                       @NotBlank
                       String password) {
}
