package web.model;

import lombok.*;

import javax.persistence.*;

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
    private Long id;

    @ToString.Include
    @Column(name = "name")
    private String name;

    @ToString.Include
    @Column(name = "last_name")
    private String surname;

    @ToString.Include
    @Column(name = "age")
    private Integer age;

}
