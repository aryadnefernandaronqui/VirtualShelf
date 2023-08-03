package virtualShelf.configs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import virtualShelf.dtos.ResponseError;

public class HandleException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity dataValidationError(MethodArgumentNotValidException exception){

        var errors = exception.getFieldErrors().stream().map(
                (error) -> new ResponseError(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }
}
