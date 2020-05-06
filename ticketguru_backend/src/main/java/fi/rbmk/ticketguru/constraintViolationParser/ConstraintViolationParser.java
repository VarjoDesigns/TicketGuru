package fi.rbmk.ticketguru.constraintViolationParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;

public class ConstraintViolationParser {

    private Set<ConstraintViolation<Object>> violations;
    private HttpStatus status;
    
    public ConstraintViolationParser(Set<ConstraintViolation<Object>> violations, HttpStatus status) {
        this.violations = violations;
        this.status = status;
    }

    public Map<String, Object> parse() {
        Map<String, Object> body = new LinkedHashMap<>();
        List<Map<String, String>> errors = new ArrayList<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        for (ConstraintViolation<?> violation : violations) {
            Map<String, String> error = new LinkedHashMap<>();
            error.put("defaultMessage", violation.getMessage());
            error.put("field", violation.getPropertyPath().toString());
            if (violation.getInvalidValue() == null) {
                error.put("rejectedValue", "null");
            } else {
                error.put("rejectedValue", violation.getInvalidValue().toString());
            }
            errors.add(error);
        }
        body.put("errors", errors);
        return body;
    }

}