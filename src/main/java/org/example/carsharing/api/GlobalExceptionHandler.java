package org.example.carsharing.api;

import jakarta.servlet.http.HttpServletRequest;
import org.example.carsharing.api.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * Обработчик глобальных исключений REST
 *
 * <p>Обрабатывает стандартные и пользовательские исключения,
 * преобразуя их в ApiError с корректными HTTP-статусами.</p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        // Создаем объект для хранения информации об ошибке
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.UNPROCESSABLE_CONTENT.value());
        error.setError(HttpStatus.UNPROCESSABLE_CONTENT.getReasonPhrase());
        error.setMessage("Validation failed");
        error.setPath(request.getRequestURI());
//        FieldError fe = ex.getBindingResult().getFieldError();

        // Сокращенный вариант
        //getField errors vse oshibki
        //potom cherez foreach
        ex.getBindingResult().getFieldErrors().forEach(fe ->
                error.getViolations().add(new ApiError.Violation(fe.getField(), fe.getDefaultMessage())));

        /*
        // Полный вариант
        List<FieldError> fe = ex.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fe) {
            error.getViolations().add(new ApiError.Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        */
        return ResponseEntity.unprocessableContent().body(error);
    }
}
