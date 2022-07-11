package web.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    @Range(min = 1)
    Long id;

    @Size(min = 2, message = "name must be min 2 symbols")
    @NotNull(message = "can't be empty")
    String name;

    @Size(min = 2, message = "surname must be min 2 symbols")
    @NotNull(message = "can't be empty")
    String surname;

    @Range(min = 14, message = "Must be digit and your age under 14")
    @NotNull(message = "can't be empty")
    String age;
}
