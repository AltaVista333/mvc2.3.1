/* (C)2022 */
package web.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Range;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Range(min = 1)
    private Long id;

    @ToString.Include
    @Column(name = "name")
    @Size(min = 2, message = "name must be min 2 symbols")
    @NotNull(message = "name must be min 2 symbols")
    private String name;

    @ToString.Include
    @Column(name = "last_name")
    @Size(min = 2, message = "surname must be min 2 symbols")
    @NotNull(message = "name must be min 2 symbols")
    private String surname;

    @ToString.Include
    @Column(name = "age")
    @Min(value = 14)
    @Max(value = 99)
    @NotNull(message = "Must be digit")
    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null ||
            Hibernate.getClass(this) !=
                Hibernate.getClass(o)) {
            return false;
        }
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
